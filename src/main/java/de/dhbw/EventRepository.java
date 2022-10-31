package de.dhbw;

import java.util.UUID;

public interface EventRepository {

	void speichereEvent(Event neuesEvent);

	Iterable<Event> findeAlleEvents() throws Exception;

	Event findeEventÜberID(UUID eventID) throws Exception;

	void aktualisiereEvent(Event event);

	void loescheEvent(UUID eventID);
}
