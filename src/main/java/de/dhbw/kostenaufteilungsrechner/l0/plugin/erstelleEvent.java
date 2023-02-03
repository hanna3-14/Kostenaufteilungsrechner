package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l1.adapters.GruppeDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Gruppe;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;

@CommandLine.Command(name = "erstelle-event", description = "erstellt ein neues Event")
public class erstelleEvent implements Runnable {

	@CommandLine.Option(names = {"-b", "--beschreibung"}, description = "Beschreibung des Events")
	String beschreibung;

	@CommandLine.Option(names = {"-g", "--gruppenname"}, description = "Filtert die Ausgabe nach dem Gruppenname")
	String gruppenName;

	@Override
	public void run() {

		EventDBAdapter eventDBAdapter = new EventDBAdapter();
		GruppeDBAdapter gruppeDBAdapter = new GruppeDBAdapter();

		Iterable<Gruppe> gruppen = gruppeDBAdapter.findeAlleGruppen();
		List<String> gruppenNamen = new ArrayList<>();

		for (Gruppe g : gruppen) {
			gruppenNamen.add(g.getGruppenName());
		}

		if (beschreibung == null) {
			beschreibung = System.console().readLine("Eventbeschreibung: ");
		}

		if (gruppenName == null) {
			System.out.println("Für welche Gruppe soll ein Event erstellt werden?");
			for (String s : gruppenNamen) {
				System.out.println("\t" + s);
			}
			gruppenName = System.console().readLine("Gruppenname: ");
		}

		while (!gruppenNamen.contains(gruppenName)) {
			System.out.println("ungültige Gruppe");
			System.out.println();
			System.out.println("Für welche Gruppe soll ein Event erstellt werden?");
			for (String s : gruppenNamen) {
				System.out.println("\t" + s);
			}
			gruppenName = System.console().readLine("Gruppenname: ");
		}

		Event event = new Event(beschreibung, gruppenName);
		System.out.println();

		System.out.println("Das folgende Event wurde erstellt:");
		System.out.println(event);

		eventDBAdapter.erstelleEvent(event);
	}
}
