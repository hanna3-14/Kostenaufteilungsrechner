package de.dhbw;

import java.util.HashMap;
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
}
