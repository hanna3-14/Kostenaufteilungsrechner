package de.dhbw;

import java.util.List;
import java.util.UUID;

public class Ausgabe {

	private UUID ausgabenID;
	private String beschreibung;
	private Geldbetrag geldbetrag;
	private int bezahlerID;
	private List<Integer> empfaengerIDs;

	public Ausgabe(String beschreibung, Geldbetrag geldbetrag, int bezahlerID, List<Integer> empfaengerIDs) {
		this.ausgabenID = UUID.randomUUID();
		this.beschreibung = beschreibung;
		this.geldbetrag = geldbetrag;
		this.bezahlerID = bezahlerID;
		this.empfaengerIDs = empfaengerIDs;
	}

	public UUID getAusgabenID() {
		return ausgabenID;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Geldbetrag getGeldbetrag() {
		return geldbetrag;
	}

	public void setGeldbetrag(Geldbetrag geldbetrag) {
		this.geldbetrag = geldbetrag;
	}

	public int getBezahlerID() {
		return bezahlerID;
	}

	public void setBezahlerID(int bezahlerID) {
		this.bezahlerID = bezahlerID;
	}

	public List<Integer> getEmpfaengerIDs() {
		return empfaengerIDs;
	}

	public void setEmpfaengerIDs(List<Integer> empfaengerIDs) {
		this.empfaengerIDs = empfaengerIDs;
	}

	@Override
	public String toString() {
		return "Ausgabe{" +
				"ausgabenID=" + ausgabenID +
				", beschreibung='" + beschreibung + '\'' +
				", geldbetrag=" + geldbetrag +
				", bezahlerID=" + bezahlerID +
				", empfaengerIDs=" + empfaengerIDs +
				'}';
	}
}
