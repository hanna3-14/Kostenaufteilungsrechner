package de.dhbw;

import java.util.Objects;

public final class Bilanz {

	private final double wert;
	private final String waehrung;

	public Bilanz(double wert) {
		this.wert = wert;
		this.waehrung = "Euro";
	}

	public double getWert() {
		return wert;
	}

	public String getWaehrung() {
		return waehrung;
	}

	public Bilanz increaseBilanz(Geldbetrag geldbetrag) {
		return new Bilanz(this.getWert() + geldbetrag.getWert());
	}

	public Bilanz decreaseBilanz(Geldbetrag geldbetrag) {
		return new Bilanz(this.getWert() - geldbetrag.getWert());
	}

	@Override
	public String toString() {
		return "Bilanz{" +
				"wert=" + wert +
				", waehrung='" + waehrung + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Bilanz bilanz = (Bilanz) o;
		return Double.compare(bilanz.wert, wert) == 0 && waehrung.equals(bilanz.waehrung);
	}

	@Override
	public int hashCode() {
		return Objects.hash(wert, waehrung);
	}
}
