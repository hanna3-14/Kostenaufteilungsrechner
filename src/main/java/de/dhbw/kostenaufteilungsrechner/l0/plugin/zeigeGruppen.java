package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.GruppeDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Gruppe;
import picocli.CommandLine;

@CommandLine.Command(name = "zeige-gruppen", mixinStandardHelpOptions = true, description = "zeigt alle Gruppen an")
public class zeigeGruppen implements Runnable {

	@CommandLine.Option(names = {"-g", "--gruppenname"}, description = "Filtert die Ausgabe nach dem Gruppenname")
	String gruppenName;

	@Override
	public void run() {

		GruppeDBAdapter gruppeDBAdapter = new GruppeDBAdapter();

		Iterable<Gruppe> gruppen = gruppeDBAdapter.findeAlleGruppen();

		if (!gruppen.iterator().hasNext()) {
			System.out.println("Noch keine Gruppen vorhanden");
			return;
		}

		if (gruppenName == null) {
			for (Gruppe g : gruppen) {
				System.out.println(g);
			}
			return;
		}

		Gruppe gruppe = gruppeDBAdapter.findeGruppeÜberName(gruppenName).orElse(null);
		if (gruppe == null) {
			System.out.println("Gruppe nicht gefunden");
			return;
		}
		System.out.println(gruppe);
	}
}
