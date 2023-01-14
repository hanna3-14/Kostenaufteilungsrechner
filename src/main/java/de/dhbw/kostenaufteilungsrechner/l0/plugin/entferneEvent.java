package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import picocli.CommandLine;

import java.util.UUID;

@CommandLine.Command(name = "entferne-event", description = "entfernt ein gesamtes Event")
public class entferneEvent implements Runnable {

	@CommandLine.Parameters(paramLabel = "eventID")
	UUID eventID;

	@Override
	public void run() {

		EventDBAdapter eventDBAdapter = new EventDBAdapter();

		eventDBAdapter.entferneEvent(eventID);
	}
}
