package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.AbrechnungDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Abrechnung;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import picocli.CommandLine;

import java.util.UUID;

@CommandLine.Command(name = "zeige-abrechnungen", mixinStandardHelpOptions = true, description = "zeigt die Abrechnungen der Events an")
public class zeigeAbrechnungen implements Runnable {

	@CommandLine.Option(names = {"-g", "--gruppenname"}, description = "Filtert die Ausgabe nach dem Gruppenname")
	String gruppenName;

	@CommandLine.Option(names = {"-id", "--abrechnungsID"}, description = "Filtert die Ausgabe nach der abrechnungsID")
	UUID abrechnungsID;

	@Override
	public void run() {
		AbrechnungDBAdapter abrechnungDBAdapter = new AbrechnungDBAdapter();

		Iterable<Abrechnung> abrechnungen = abrechnungDBAdapter.findeAlleAbrechnungen();

		if (!abrechnungen.iterator().hasNext()) {
			System.out.println("Noch keine Abrechnungen vorhanden");
			return;
		}

		if (gruppenName != null) {
			EventDBAdapter eventDBAdapter = new EventDBAdapter();
			Iterable<Event> events = eventDBAdapter.findeAlleEvents();

			for (Event e : events) {
				if (e.getGruppenName().equals(gruppenName)) {
					System.out.println(abrechnungDBAdapter.findeAbrechnungÜberID(e.getAbrechnungsID()));
				}
			}
		}

		if (abrechnungsID != null) {
			for (Abrechnung a : abrechnungen) {
				if (a.getAbrechnungsID().equals(abrechnungsID)) {
					System.out.println(a);
				}
			}
			return;
		}

		for (Abrechnung a : abrechnungen) {
			System.out.println(a);
		}
	}
}
