package de.dhbw.kostenaufteilungsrechner.l3.domain;

import com.google.gson.annotations.Expose;

public class Mitglied {

	@Expose
	private int mitgliedsID;
	@Expose
	private String name;

	public Mitglied(int mitgliedsID, String name) {
		this.mitgliedsID = mitgliedsID;
		this.name = name;
	}

	public int getMitgliedsID() {
		return mitgliedsID;
	}

	public void setMitgliedsID(int mitgliedsID) {
		this.mitgliedsID = mitgliedsID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\t" + mitgliedsID + " " + name + System.lineSeparator();
	}
}
