package de.dhbw.kostenaufteilungsrechner.l1.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Abrechnung;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Ausgabe;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import de.dhbw.kostenaufteilungsrechner.l3.domain.EventRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EventDBAdapter implements EventRepository {

	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

	String database = "datenbank/events.json";

	List<Event> eventList = new ArrayList<>();

	@Override
	public void erstelleEvent(Event neuesEvent) {
		try {
			Iterable<Event> events = this.findeAlleEvents();
			for (Event event : events) {
				eventList.add(event);
			}
		} catch (Exception e) {

		}
		eventList.add(neuesEvent);
		try (BufferedWriter out = new BufferedWriter(new FileWriter(database, false))) {
			out.write(gson.toJson(eventList));
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
	}

	@Override
	public Iterable<Event> findeAlleEvents() {
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			Iterable<Event> events = gson.fromJson(br, new TypeToken<List<Event>>() {
			}.getType());
			return events;
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		return null;
	}

	@Override
	public Optional<Event> findeEventÜberID(UUID eventID) {
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			Iterable<Event> events = gson.fromJson(br, new TypeToken<List<Event>>() {
			}.getType());
			for (Event e : events) {
				if (e.getEventID().equals(eventID)) {
					return Optional.of(e);
				}
			}
			return Optional.empty();
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		return Optional.empty();
	}

	@Override
	public void aktualisiereEvent(Event event) {
		List<Event> events = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			events = gson.fromJson(br, new TypeToken<List<Event>>() {
			}.getType());
			for (Event e : events) {
				if (e.getEventID().equals(event.getEventID())) {
					e.setBeschreibung(event.getBeschreibung());
					e.setGruppenName(event.getGruppenName());
				}
			}
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		try (BufferedWriter out = new BufferedWriter(new FileWriter(database, false))) {
			out.write(gson.toJson(events));
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
	}

	@Override
	public void fügeNeueAusgabeHinzu(UUID eventID, Ausgabe ausgabe, Abrechnung abrechnung) {
		List<Event> events = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			events = gson.fromJson(br, new TypeToken<List<Event>>() {
			}.getType());
			for (Event e : events) {
				if (e.getEventID().equals(eventID)) {
					e.meldeAn(abrechnung);
					e.addAusgabe(ausgabe);
				}
			}
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		try (BufferedWriter out = new BufferedWriter(new FileWriter(database, false))) {
			out.write(gson.toJson(events));
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
	}

	@Override
	public void entferneEvent(UUID eventID) {
		List<Event> events = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			events = gson.fromJson(br, new TypeToken<List<Event>>() {
			}.getType());
			events = events.stream()
					.filter(event -> !event.getEventID().equals(eventID))
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		try (BufferedWriter out = new BufferedWriter(new FileWriter(database, false))) {
			out.write(gson.toJson(events));
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
	}
}
