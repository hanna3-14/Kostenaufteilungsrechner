package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l1.adapters.GruppeDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Gruppe;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CommandLine.Command(name = "erstelle-gruppe", description = "erstellt eine neue Gruppe")
public class erstelleGruppe implements Runnable {

	@CommandLine.Option(names = {"-g", "--gruppenname"}, description = "Angabe des Gruppennamens")
	String gruppenName;

	@CommandLine.Option(names = {"-n", "--namensliste"}, description = "Namensliste der Gruppenmitglieder")
	String namen;

	@Override
	public void run() {

		GruppeDBAdapter gruppeDBAdapter = new GruppeDBAdapter();

		Iterable<Gruppe> gruppen = gruppeDBAdapter.findeAlleGruppen();
		List<String> gruppenNamen = new ArrayList<>();

		for (Gruppe g : gruppen) {
			gruppenNamen.add(g.getGruppenName());
		}

		if (gruppenName == null) {
			gruppenName = System.console().readLine("Gruppenname: ");
		}
		
		while (gruppenNamen.contains(gruppenName)) {
			System.out.println("Dieser Gruppenname existiert bereits! Bitte wähle einen anderen Gruppenname!");

			gruppenName = System.console().readLine("Gruppenname: ");
		}

		if (namen == null) {
			namen = System.console().readLine("Namensliste: ");
		}

		List<String> namensListe = new ArrayList<>();
		String[] namensArray = namen.replaceAll(",", "").split(" ");

		for (String s : namensArray) {
			namensListe.add(s);
		}

		Gruppe gruppe = new Gruppe(gruppenName, namensListe);
		System.out.println();

		System.out.println("Die folgende Gruppe wurde erstellt:");
		System.out.println(gruppe);

		gruppeDBAdapter.erstelleGruppe(gruppe);
	}
}
