package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.List;

public interface EventBeobachter {

	void aktualisiere(List<Ausgabe> ausgabenListe);

}
