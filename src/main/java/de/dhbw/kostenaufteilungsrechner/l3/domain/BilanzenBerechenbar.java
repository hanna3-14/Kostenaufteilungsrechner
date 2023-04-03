package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.HashMap;
import java.util.List;

public interface BilanzenBerechenbar {

	HashMap<Integer, Bilanz> berechneBilanzen(List<Ausgabe> ausgabenListe);

}
