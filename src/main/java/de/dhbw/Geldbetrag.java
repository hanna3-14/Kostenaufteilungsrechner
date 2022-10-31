package de.dhbw;

import java.util.Objects;

public final class Geldbetrag {

	private final double wert;
	private final String waehrung;

	public Geldbetrag(double wert) {
		this.wert = wert;
		this.waehrung = "Euro";
	}

	public double getWert() {
		return wert;
	}

	public Geldbetrag increaseBetrag(Geldbetrag geldbetrag) {
		return new Geldbetrag(this.getWert() + geldbetrag.getWert());
	}

	public Geldbetrag decreaseBetrag(Geldbetrag geldbetrag) {
		return new Geldbetrag(this.getWert() - geldbetrag.getWert());
	}

	public String getWaehrung() {
		return waehrung;
	}

	@Override
	public String toString() {
		return "Geldbetrag{" +
				"wert=" + wert +
				", waehrung='" + waehrung + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Geldbetrag betrag1 = (Geldbetrag) o;
		return Double.compare(betrag1.wert, wert) == 0 && Objects.equals(waehrung, betrag1.waehrung);
	}

	@Override
	public int hashCode() {
		return Objects.hash(wert, waehrung);
	}
}
