package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import picocli.CommandLine;

@CommandLine.Command(name = "erstelle-event", description = "erstellt ein neues Event")
public class erstelleEvent implements Runnable {

	@CommandLine.Option(names = {"-b", "--beschreibung"}, description = "Beschreibung des Events")
	String beschreibung;

	@CommandLine.Option(names = {"-g", "--gruppenname"}, description = "Filtert die Ausgabe nach dem Gruppenname")
	String gruppenName;

	@Override
	public void run() {

		EventDBAdapter eventDBAdapter = new EventDBAdapter();

		if (beschreibung == null) {
			beschreibung = System.console().readLine("Eventbeschreibung: ");
		}

		if (gruppenName == null) {
			gruppenName = System.console().readLine("Gruppenname: ");
		}

		Event event = new Event(beschreibung, gruppenName);
		System.out.println();

		System.out.println("Das folgende Event wurde erstellt:");
		System.out.println(event);

		eventDBAdapter.erstelleEvent(event);
	}
}
