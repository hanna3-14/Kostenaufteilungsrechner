package de.dhbw.kostenaufteilungsrechner.l3.domain;

import com.google.gson.annotations.Expose;
import de.dhbw.kostenaufteilungsrechner.l2.application.BilanzenBerechner;
import de.dhbw.kostenaufteilungsrechner.l2.application.GesamtausgabenBerechner;
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

	@Override
	public void aktualisiere(List<Ausgabe> ausgabenListe) {
		GesamtausgabenBerechner gesamtausgabenBerechner = new GesamtausgabenBerechner();
		this.setGesamtausgaben(gesamtausgabenBerechner.anhandVon(ausgabenListe));
		BilanzenBerechner bilanzenBerechner = new BilanzenBerechner();
		this.setBilanzenMap(bilanzenBerechner.anhandVon(ausgabenListe));
	}
}