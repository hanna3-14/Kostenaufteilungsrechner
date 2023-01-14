package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import picocli.CommandLine;

import java.util.UUID;

@CommandLine.Command(name = "zeige-event", description = "zeigt Event zu EventID an")
public class zeigeEvent implements Runnable {

	@CommandLine.Parameters(paramLabel = "eventID")
	UUID eventID;

	@Override
	public void run() {

		EventDBAdapter eventDBAdapter = new EventDBAdapter();

		Event event = eventDBAdapter.findeEventÜberID(eventID).orElse(null);

		if (event == null) {
			System.out.println("Es ist kein Event mit der angegebenen eventID vorhanden!");
			return;
		}

		System.out.println(event);
	}
}
