package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class EventSubjekt {

	private List<EventBeobachter> angemeldeteBeobachter;

	public EventSubjekt() {
		super();
		this.angemeldeteBeobachter = new ArrayList<>();
	}

	public void meldeAn(UUID abrechnungsID) {
		if (this.angemeldeteBeobachter == null) {
			this.angemeldeteBeobachter = new ArrayList<>();
		}
		this.angemeldeteBeobachter.add(new Abrechnung(abrechnungsID));
	}

	public void meldeAb(UUID abrechnungsID) {
		this.angemeldeteBeobachter.remove(abrechnungsID);
	}

	protected void benachrichtige(List<Ausgabe> ausgabenListe) {
		this.angemeldeteBeobachter.forEach(b -> b.aktualisiere(ausgabenListe));
	}
}
