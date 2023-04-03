package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository {

	void erstelleEvent(Event neuesEvent);

	Iterable<Event> findeAlleEvents();

	Optional<Event> findeEventÜberID(UUID eventID);

	void aktualisiereEvent(Event event);

	void fügeNeueAusgabeHinzu(UUID eventID, Ausgabe ausgabe, Abrechnung abrechnung);

	void entferneEvent(UUID eventID);
}
