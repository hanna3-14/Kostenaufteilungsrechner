package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.ArrayList;
import java.util.List;

public class Gruppe {

	private String gruppenName;
	private List<Mitglied> mitgliederListe = new ArrayList<>();

	public Gruppe(String gruppenName, List<String> namensListe) {
		this.gruppenName = gruppenName;
		for (int i = 0; i < namensListe.size(); i++) {
			this.mitgliederListe.add(new Mitglied(i + 1, namensListe.get(i)));
		}
	}

	public String getGruppenName() {
		return gruppenName;
	}

	public void setGruppenName(String gruppenName) {
		this.gruppenName = gruppenName;
	}

	public List<Mitglied> getMitgliederListe() {
		return mitgliederListe;
	}

	public void setMitgliederListe(List<Mitglied> mitgliederListe) {
		this.mitgliederListe = mitgliederListe;
	}

	@Override
	public String toString() {
		return "Gruppe{" +
				"gruppenName='" + gruppenName + '\'' +
				", mitgliederListe=" + mitgliederListe +
				'}';
	}
}
