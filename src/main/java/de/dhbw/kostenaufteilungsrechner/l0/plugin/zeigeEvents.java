package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import picocli.CommandLine;

@CommandLine.Command(name = "zeige-events", description = "zeigt alle Events einer Gruppe an (Parameter: Gruppenname)")
public class zeigeEvents implements Runnable {

	@CommandLine.Parameters(paramLabel = "gruppenname")
	String gruppenName;

	@Override
	public void run() {

		EventDBAdapter eventDBAdapter = new EventDBAdapter();

		Iterable<Event> events = eventDBAdapter.findeAlleEvents();

		if (!events.iterator().hasNext()) {
			System.out.println("Noch keine Events vorhanden");
			return;
		}

		for (Event e : events) {
			if (e.getGruppenName().equals(gruppenName)) {
				System.out.println(e);
			}
		}
	}
}
