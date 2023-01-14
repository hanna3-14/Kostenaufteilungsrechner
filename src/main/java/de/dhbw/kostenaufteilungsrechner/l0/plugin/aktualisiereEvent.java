package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import picocli.CommandLine;

import java.util.UUID;

@CommandLine.Command(name = "aktualisiere-event", description = "aktualisiert die Beschreibung und den Gruppennamen eines Events")
public class aktualisiereEvent implements Runnable {

	@CommandLine.Parameters(paramLabel = "eventID")
	UUID eventID;

	@CommandLine.Option(names = {"-b", "--beschreibung"}, description = "neuer Beschreibungstext des Events")
	String beschreibung;

	@CommandLine.Option(names = {"-g", "--gruppenname"}, description = "neuer Gruppenname des Events")
	String gruppenName;

	@Override
	public void run() {

		EventDBAdapter eventDBAdapter = new EventDBAdapter();

		Event event = eventDBAdapter.findeEventÜberID(eventID).orElse(null);

		if (event == null) {
			System.out.println("Es ist kein Event mit der angegebenen eventID vorhanden!");
			return;
		}

		if (beschreibung != null) {
			event.setBeschreibung(beschreibung);
		}
		if (gruppenName != null) {
			event.setGruppenName(gruppenName);
		}
		eventDBAdapter.aktualisiereEvent(event);
	}
}
