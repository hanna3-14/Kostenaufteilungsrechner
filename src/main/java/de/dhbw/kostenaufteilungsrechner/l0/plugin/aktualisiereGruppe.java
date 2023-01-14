package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.GruppeDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Gruppe;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Mitglied;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;

@CommandLine.Command(name = "aktualisiere-gruppe", description = "aktualisiert die Mitgliederliste einer Gruppe")
public class aktualisiereGruppe implements Runnable {

	@CommandLine.Parameters(paramLabel = "Gruppenname")
	String gruppenName;

	@CommandLine.Option(names = {"-n", "--namensliste"}, description = "Namensliste der Gruppenmitglieder")
	String namen;

	@Override
	public void run() {

		GruppeDBAdapter gruppeDBAdapter = new GruppeDBAdapter();

		Gruppe gruppe = gruppeDBAdapter.findeGruppeÜberName(gruppenName).orElse(null);

		if (gruppe == null) {
			System.out.println("Es ist keine Gruppe mit dem angegebenen Gruppennamen vorhanden!");
			return;
		}

		if (namen != null) {
			List<Mitglied> mitgliederListe = new ArrayList<>();
			String[] namensArray = namen.split(" ");

			for (int i = 0; i < namensArray.length; i++) {
				mitgliederListe.add(new Mitglied(i + 1, namensArray[i]));
			}
			gruppe.setMitgliederListe(mitgliederListe);
		}
		gruppeDBAdapter.aktualisiereGruppe(gruppe);
	}
}
