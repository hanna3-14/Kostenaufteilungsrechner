package de.dhbw.kostenaufteilungsrechner.l3.domain;

import com.google.gson.annotations.Expose;
import de.dhbw.kostenaufteilungsrechner.l4.abstraction.Euro;

import java.util.*;

public class Abrechnung implements EventBeobachter {

	@Expose
	private final UUID abrechnungsID;
	@Expose
	private Geldbetrag gesamtausgaben;
	@Expose
	private HashMap<Integer, Bilanz> bilanzenMap;

	public Abrechnung(UUID abrechnungsID, Geldbetrag gesamtausgaben, HashMap<Integer, Bilanz> bilanzenMap) {
		this.abrechnungsID = abrechnungsID;
		this.gesamtausgaben = gesamtausgaben;
		this.bilanzenMap = bilanzenMap;
	}

	public Abrechnung(UUID abrechnungsID) {
		this(abrechnungsID, new Geldbetrag(new Euro(0, 0)), new HashMap<>());
	}

	public UUID getAbrechnungsID() {
		return abrechnungsID;
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
				"Gesamtausgaben: " + gesamtausgaben + System.lineSeparator() +
				"Bilanzen: " + bilanzenstring;
	}

	public void berechneGesamtausgaben(List<Ausgabe> ausgabenListe) {
		// Berechnung der Gesamtausgaben
		Geldbetrag gesamtausgaben = new Geldbetrag(new Euro(0, 0));
		for (Ausgabe a : ausgabenListe) {
			gesamtausgaben = gesamtausgaben.increaseGeldbetrag(a.getGeldbetrag());
		}
		this.setGesamtausgaben(gesamtausgaben);
	}

	public void berechneBilanzen(List<Ausgabe> ausgabenListe) {
		Bilanz startbilanz = new Bilanz(new Euro(0, 0));
		HashMap<Integer, Bilanz> bilanzen = new HashMap<>();

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
			Geldbetrag anteiligerBetrag = new Geldbetrag(Euro.divide(a.getGeldbetrag().getWert(), a.getEmpfaengerIDs().size()));
			for (int i : bilanzen.keySet()) {
				if (a.getBezahlerID() == i) {
					bilanzen.computeIfPresent(i, (k, v) -> v.increaseBilanz(a.getGeldbetrag()));
				}
				if (a.getEmpfaengerIDs().contains(i)) {
					bilanzen.computeIfPresent(i, (k, v) -> v.decreaseBilanz(anteiligerBetrag));
				}
			}
		}
		this.setBilanzenMap(bilanzen);
	}

	@Override
	public void aktualisiere(List<Ausgabe> ausgabenListe) {
		this.berechneGesamtausgaben(ausgabenListe);
		this.berechneBilanzen(ausgabenListe);
	}
}