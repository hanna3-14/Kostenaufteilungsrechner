package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import picocli.CommandLine;

import java.util.UUID;

@CommandLine.Command(name = "zeige-events", mixinStandardHelpOptions = true, description = "zeigt alle Events einer Gruppe an")
public class zeigeEvents implements Runnable {

	@CommandLine.Option(names = {"-g", "--gruppenname"}, description = "Filtert die Ausgabe nach dem Gruppenname")
	String gruppenName;

	@CommandLine.Option(names = {"-id", "--eventID"}, description = "Filtert die Ausgabe nach der eventID")
	UUID eventID;

	@Override
	public void run() {

		EventDBAdapter eventDBAdapter = new EventDBAdapter();

		Iterable<Event> events = eventDBAdapter.findeAlleEvents();

		if (!events.iterator().hasNext()) {
			System.out.println("Noch keine Events vorhanden");
			return;
		}

		if (eventID != null) {
			for (Event e : events) {
				if (e.getEventID().equals(eventID)) {
					System.out.println(e);
				}
			}
			return;
		}

		if (gruppenName != null) {
			for (Event e : events) {
				if (e.getGruppenName().equals(gruppenName)) {
					System.out.println(e);
				}
			}
			return;
		}

		for (Event e : events) {
			System.out.println(e);
		}
	}
}
