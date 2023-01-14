package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.Objects;

public final class Geldbetrag {

	private final double wert;
	private final String waehrung;

	public Geldbetrag(double wert) {
		if (wert >= 0.0) {
			this.wert = wert;
		} else {
			this.wert = 0.0;
		}
		this.waehrung = "Euro";
	}

	public double getWert() {
		return wert;
	}

	public String getWaehrung() {
		return waehrung;
	}

	public Geldbetrag increaseGeldbetrag(Geldbetrag geldbetrag) {
		return new Geldbetrag(this.getWert() + geldbetrag.getWert());
	}

	@Override
	public String toString() {
		return wert + " " + waehrung;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Geldbetrag that = (Geldbetrag) o;
		return Double.compare(that.wert, wert) == 0 && waehrung.equals(that.waehrung);
	}

	@Override
	public int hashCode() {
		return Objects.hash(wert, waehrung);
	}
}
