package de.dhbw.kostenaufteilungsrechner.l3.domain;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Gruppe {

	@Expose
	private String gruppenName;
	@Expose
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

	private String getMitgliederString() {
		String ausgaben = System.lineSeparator();
		for (Mitglied m : mitgliederListe) {
			ausgaben += m.toString();
		}
		return ausgaben;
	}

	@Override
	public String toString() {
		String mitgliederstring = this.getMitgliederString();
		return "Gruppenname: " + gruppenName + System.lineSeparator() +
				"Mitglieder: " + mitgliederstring;
	}
}
