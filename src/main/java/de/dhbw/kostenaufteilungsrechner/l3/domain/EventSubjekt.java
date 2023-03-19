package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.List;
import java.util.UUID;

public abstract class EventSubjekt {

	private EventBeobachter abrechnung;

	public EventSubjekt() {
		super();
		abrechnung = new Abrechnung(null);
	}

	public EventBeobachter getAbrechnung() {
		return abrechnung;
	}

	public void meldeAn(UUID abrechnungsID) {
		this.abrechnung = new Abrechnung(abrechnungsID);
	}

	protected void benachrichtige(List<Ausgabe> ausgabenListe) {
		this.abrechnung.aktualisiere(ausgabenListe);
	}
}
