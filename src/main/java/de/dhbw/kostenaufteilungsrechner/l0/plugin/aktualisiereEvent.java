package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l1.adapters.GruppeDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Gruppe;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
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
		GruppeDBAdapter gruppeDBAdapter = new GruppeDBAdapter();

		Iterable<Gruppe> gruppen = gruppeDBAdapter.findeAlleGruppen();
		List<String> gruppenNamen = new ArrayList<>();

		for (Gruppe g : gruppen) {
			gruppenNamen.add(g.getGruppenName());
		}

		Event event = eventDBAdapter.findeEventÜberID(eventID).orElse(null);

		if (event == null) {
			System.out.println("Es ist kein Event mit der angegebenen eventID vorhanden!");
			return;
		}

		if (beschreibung != null) {
			event.setBeschreibung(beschreibung);
		}

		if (gruppenName != null && gruppenNamen.contains(gruppenName)) {
			event.setGruppenName(gruppenName);
			System.out.println("Für Event " + eventID + " wurde der Gruppenname aktualisiert auf: " + gruppenName);
		}

		if (gruppenName != null && !gruppenNamen.contains(gruppenName)) {
			System.out.println("ungültiger Gruppenname");
			System.out.println();
			System.out.println("Verfügbare Gruppen:");
			for (String s : gruppenNamen) {
				System.out.println("\t" + s);
			}
		}

		eventDBAdapter.aktualisiereEvent(event);
	}
}
