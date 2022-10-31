package de.dhbw;

public class Mitglied {

	private int mitgliedsID;
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
		return "Mitglied{" +
				"mitgliedsID=" + mitgliedsID +
				", name='" + name + '\'' +
				'}';
	}
}
