package de.dhbw;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class Abrechnung {

	private final UUID abrechnungsID;
	private UUID eventID;
	private Geldbetrag gesamtausgaben;
	private HashMap<Integer, Geldbetrag> bilanzen;

	public Abrechnung(UUID eventID, Geldbetrag gesamtausgaben, HashMap<Integer, Geldbetrag> bilanzen) {
		this.abrechnungsID = UUID.randomUUID();
		this.eventID = eventID;
		this.gesamtausgaben = gesamtausgaben;
		this.bilanzen = bilanzen;
	}

	public Abrechnung(UUID eventID) {
		this(eventID, new Geldbetrag(0.0), new HashMap<>());
	}

	public UUID getAbrechnungsID() {
		return abrechnungsID;
	}

	public UUID getEventID() {
		return eventID;
	}

	public void setEventID(UUID eventID) {
		this.eventID = eventID;
	}

	public Geldbetrag getGesamtausgaben() {
		return gesamtausgaben;
	}

	public void setGesamtausgaben(Geldbetrag gesamtausgaben) {
		this.gesamtausgaben = gesamtausgaben;
	}

	public HashMap<Integer, Geldbetrag> getBilanzen() {
		return bilanzen;
	}

	public void setBilanzen(HashMap<Integer, Geldbetrag> bilanzen) {
		this.bilanzen = bilanzen;
	}

	@Override
	public String toString() {
		return "Abrechnung{" +
				"abrechnungsID=" + abrechnungsID +
				", eventID=" + eventID +
				", gesamtausgaben=" + gesamtausgaben +
				", bilanzen=" + bilanzen +
				'}';
	}

	public void berechneGesamtausgaben(Event event) {
		List<Ausgabe> ausgabenListe = event.getAusgabenListe();

		// Berechnung der Gesamtausgaben
		Geldbetrag gesamtausgaben = new Geldbetrag(0.0);
		for (Ausgabe a : ausgabenListe) {
			gesamtausgaben = new Geldbetrag(gesamtausgaben.getWert() + a.getGeldbetrag().getWert());
		}
		this.setGesamtausgaben(gesamtausgaben);
	}

	public void berechneBilanzen(Event event) {
		List<Ausgabe> ausgabenListe = event.getAusgabenListe();

		Geldbetrag startbetrag = new Geldbetrag(0.0);
		LinkedHashMap<Integer, Geldbetrag> bilanzen = new LinkedHashMap<>();

		// Alle Mitglieder, die an einer Ausgabe des Events beteiligt sind, werden mit einem Startbetrag von 0.00 Euro hinzugefügt
		for (Ausgabe a : ausgabenListe) {
			bilanzen.put(a.getBezahlerID(), startbetrag);
		}
		for (Ausgabe a : ausgabenListe) {
			for (int i : a.getEmpfaengerIDs()) {
				bilanzen.put(i, startbetrag);
			}
		}

		// Berechnung der Bilanzen für die einzelnen Mitglieder
		for (Ausgabe a : ausgabenListe) {
			for (int i : bilanzen.keySet()) {
				if (a.getBezahlerID() == i) {
					bilanzen.computeIfPresent(i, (k, v) -> v.increaseBetrag(a.getGeldbetrag()));
				}
				Geldbetrag anteiligerBetrag = new Geldbetrag(a.getGeldbetrag().getWert() / a.getEmpfaengerIDs().size());
				if (a.getEmpfaengerIDs().contains(i)) {
					bilanzen.computeIfPresent(i, (k, v) -> v.decreaseBetrag(anteiligerBetrag));
				}
			}
		}
		this.setBilanzen(bilanzen);
	}
}
