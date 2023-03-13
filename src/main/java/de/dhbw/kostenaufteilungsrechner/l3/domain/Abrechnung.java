package de.dhbw.kostenaufteilungsrechner.l3.domain;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class Abrechnung {

	private CurrencyUnit euro = Monetary.getCurrency("EUR");
	private final UUID abrechnungsID;
	private UUID eventID;
	private Geldbetrag gesamtausgaben;
	private HashMap<Integer, Bilanz> bilanzenMap;

	public Abrechnung(UUID eventID, Geldbetrag gesamtausgaben, HashMap<Integer, Bilanz> bilanzenMap) {
		this.abrechnungsID = UUID.randomUUID();
		this.eventID = eventID;
		this.gesamtausgaben = gesamtausgaben;
		this.bilanzenMap = bilanzenMap;
	}

	public Abrechnung(UUID eventID) {
		this(eventID, new Geldbetrag(Money.of(0.0, Monetary.getCurrency("EUR"))), new HashMap<>());
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

	public HashMap<Integer, Bilanz> getBilanzenMap() {
		return bilanzenMap;
	}

	public void setBilanzenMap(HashMap<Integer, Bilanz> bilanzenMap) {
		this.bilanzenMap = bilanzenMap;
	}

	private String getBilanzenString() {
		String bilanzen = System.lineSeparator();
		for (int i = 0; i < bilanzenMap.size(); i++) {
			bilanzen += "\t" + (i + 1) + " " + bilanzenMap.get(i + 1) + System.lineSeparator();
		}
		return bilanzen;
	}


	@Override
	public String toString() {
		String bilanzenstring = this.getBilanzenString();
		return "AbrechnungsID: " + abrechnungsID + System.lineSeparator() +
				"EventID: " + eventID + System.lineSeparator() +
				"Gesamtausgaben: " + gesamtausgaben + System.lineSeparator() +
				"Bilanzen: " + bilanzenstring;
	}

	public void berechneGesamtausgaben(Event event) {
		List<Ausgabe> ausgabenListe = event.getAusgabenListe();

		// Berechnung der Gesamtausgaben
		Geldbetrag gesamtausgaben = new Geldbetrag(Money.of(0.0, euro));
		for (Ausgabe a : ausgabenListe) {
			gesamtausgaben = gesamtausgaben.increaseGeldbetrag(a.getGeldbetrag());
		}
		this.setGesamtausgaben(gesamtausgaben);
	}

	public void berechneBilanzen(Event event) {
		List<Ausgabe> ausgabenListe = event.getAusgabenListe();

		Bilanz startbilanz = new Bilanz(Money.of(0.0, euro));
		LinkedHashMap<Integer, Bilanz> bilanzen = new LinkedHashMap<>();

		// Alle Mitglieder, die an einer Ausgabe des Events beteiligt sind, werden mit einer Startbilanz von 0.00 Euro hinzugefügt
		for (Ausgabe a : ausgabenListe) {
			bilanzen.put(a.getBezahlerID(), startbilanz);
		}
		for (Ausgabe a : ausgabenListe) {
			for (int i : a.getEmpfaengerIDs()) {
				bilanzen.put(i, startbilanz);
			}
		}

		// Berechnung der Bilanzen für die einzelnen Mitglieder
		for (Ausgabe a : ausgabenListe) {
			for (int i : bilanzen.keySet()) {
				if (a.getBezahlerID() == i) {
					bilanzen.computeIfPresent(i, (k, v) -> v.increaseBilanz(a.getGeldbetrag()));
				}
				Geldbetrag anteiligerBetrag = new Geldbetrag(a.getGeldbetrag().getWert().divide(a.getEmpfaengerIDs().size()));
				if (a.getEmpfaengerIDs().contains(i)) {
					bilanzen.computeIfPresent(i, (k, v) -> v.decreaseBilanz(anteiligerBetrag));
				}
			}
		}
		this.setBilanzenMap(bilanzen);
	}
}
