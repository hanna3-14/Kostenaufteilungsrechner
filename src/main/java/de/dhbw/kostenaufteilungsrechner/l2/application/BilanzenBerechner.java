package de.dhbw.kostenaufteilungsrechner.l2.application;

import de.dhbw.kostenaufteilungsrechner.l3.domain.Ausgabe;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Bilanz;
import de.dhbw.kostenaufteilungsrechner.l3.domain.BilanzenBerechenbar;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Geldbetrag;
import de.dhbw.kostenaufteilungsrechner.l4.abstraction.Euro;

import java.util.HashMap;
import java.util.List;

public class BilanzenBerechner implements BilanzenBerechenbar {

	@Override
	public HashMap<Integer, Bilanz> berechneBilanzen(List<Ausgabe> ausgabenListe) {

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

		return bilanzen;
	}
}
