package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Event {

	private UUID eventID;
	private String beschreibung;
	private String gruppenName;
	private List<Ausgabe> ausgabenListe = new ArrayList<>();

	public Event(String beschreibung, String gruppenName) {
		this.eventID = UUID.randomUUID();
		this.beschreibung = beschreibung;
		this.gruppenName = gruppenName;
	}

	public UUID getEventID() {
		return eventID;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getGruppenName() {
		return gruppenName;
	}

	public void setGruppenName(String gruppenName) {
		this.gruppenName = gruppenName;
	}

	public List<Ausgabe> getAusgabenListe() {
		return ausgabenListe;
	}

	public void setAusgabenListe(List<Ausgabe> ausgabenListe) {
		this.ausgabenListe = ausgabenListe;
	}

	public void addAusgabe(Ausgabe ausgabe) {
		this.ausgabenListe.add(ausgabe);
	}

	private String getAusgabenString() {
		String ausgaben = System.lineSeparator();
		for (Ausgabe a : ausgabenListe) {
			ausgaben += a.toString();
		}
		return ausgaben;
	}

	@Override
	public String toString() {
		String ausgabenstring = this.getAusgabenString();
		return "EventID: " + eventID + System.lineSeparator() +
				"Beschreibung: " + beschreibung + System.lineSeparator() +
				"Gruppenname: " + gruppenName + System.lineSeparator() +
				"Ausgaben: " + ausgabenstring;
	}
}
