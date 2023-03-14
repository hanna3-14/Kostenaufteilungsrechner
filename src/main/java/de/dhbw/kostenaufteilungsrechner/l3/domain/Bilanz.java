package de.dhbw.kostenaufteilungsrechner.l3.domain;

import de.dhbw.kostenaufteilungsrechner.l4.abstraction.Euro;

import java.util.Objects;

public final class Bilanz {

	private final Euro wert;

	public Bilanz(Euro wert) {
		this.wert = wert;
	}

	public Euro getWert() {
		return wert;
	}

	public Bilanz increaseBilanz(Geldbetrag geldbetrag) {
		return new Bilanz(Euro.add(this.getWert(), geldbetrag.getWert()));
	}

	public Bilanz decreaseBilanz(Geldbetrag geldbetrag) {
		return new Bilanz(Euro.subtract(this.getWert(), geldbetrag.getWert()));
	}

	@Override
	public String toString() {
		return wert.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Bilanz bilanz = (Bilanz) o;
		return Objects.equals(wert, bilanz.wert);
	}

	@Override
	public int hashCode() {
		return Objects.hash(wert);
	}
}
