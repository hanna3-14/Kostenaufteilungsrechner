package de.dhbw.kostenaufteilungsrechner.l3.domain;

import com.google.gson.annotations.Expose;
import de.dhbw.kostenaufteilungsrechner.l4.abstraction.Euro;

import java.util.Objects;

public final class Geldbetrag {

	@Expose
	private final Euro wert;

	public Geldbetrag(Euro wert) {
		if (wert.getEuroBetrag() < 0 || wert.getCentBetrag() < 0) {
			this.wert = new Euro(0, 0);
		} else {
			this.wert = wert;
		}
	}

	public Euro getWert() {
		return wert;
	}

	public Geldbetrag increaseGeldbetrag(Geldbetrag geldbetrag) {
		return new Geldbetrag(Euro.add(this.getWert(), geldbetrag.getWert()));
	}

	@Override
	public String toString() {
		return wert.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Geldbetrag that = (Geldbetrag) o;
		return Objects.equals(wert, that.wert);
	}

	@Override
	public int hashCode() {
		return Objects.hash(wert);
	}
}
