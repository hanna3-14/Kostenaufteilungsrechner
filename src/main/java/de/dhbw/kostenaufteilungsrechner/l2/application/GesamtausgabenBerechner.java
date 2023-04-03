package de.dhbw.kostenaufteilungsrechner.l2.application;

import de.dhbw.kostenaufteilungsrechner.l3.domain.Ausgabe;
import de.dhbw.kostenaufteilungsrechner.l3.domain.GesamtausgabenBerechenbar;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Geldbetrag;
import de.dhbw.kostenaufteilungsrechner.l4.abstraction.Euro;

import java.util.List;

public class GesamtausgabenBerechner implements GesamtausgabenBerechenbar {

	@Override
	public Geldbetrag berechneGesamtausgaben(List<Ausgabe> ausgabenListe) {

		Geldbetrag gesamtausgaben = new Geldbetrag(new Euro(0, 0));

		for (Ausgabe a : ausgabenListe) {
			gesamtausgaben = gesamtausgaben.increaseGeldbetrag(a.getGeldbetrag());
		}

		return gesamtausgaben;
	}
}
