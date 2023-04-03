package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.List;

public interface GesamtausgabenBerechenbar {

	Geldbetrag berechneGesamtausgaben(List<Ausgabe> ausgabenListe);

}
