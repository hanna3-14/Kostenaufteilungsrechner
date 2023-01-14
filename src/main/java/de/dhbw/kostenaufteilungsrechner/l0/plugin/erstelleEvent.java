package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import picocli.CommandLine;

import java.util.Scanner;

@CommandLine.Command(name = "erstelle-event", description = "erstellt ein neues Event")
public class erstelleEvent implements Runnable {

	@Override
	public void run() {

		EventDBAdapter eventDBAdapter = new EventDBAdapter();

		Scanner scan = new Scanner(System.in);

		System.out.println("Eventbeschreibung:");
		String beschreibung = scan.nextLine();

		System.out.println("Gruppenname:");
		String gruppenName = scan.next();

		Event event = new Event(beschreibung, gruppenName);
		System.out.println();

		System.out.println("Das folgende Event wurde erstellt:");
		System.out.println(event);

		eventDBAdapter.erstelleEvent(event);
	}
}
