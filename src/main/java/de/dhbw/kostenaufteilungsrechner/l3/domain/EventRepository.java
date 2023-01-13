package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository {

	void erstelleEvent(Event neuesEvent);

	Iterable<Event> findeAlleEvents() throws Exception;

	Optional<Event> findeEventÜberID(UUID eventID) throws Exception;

	void aktualisiereEvent(Event event);

	void entferneEvent(UUID eventID);
}
