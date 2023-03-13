package de.dhbw.kostenaufteilungsrechner.l3.domain;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Objects;

public final class Geldbetrag {
	private CurrencyUnit euro = Monetary.getCurrency("EUR");
	private Money wert;

	public Geldbetrag(Money wert) {
		if (wert.isPositiveOrZero()) {
			this.wert = wert;
		} else {
			this.wert = Money.of(0.0, euro);
		}
	}

	public Money getWert() {
		return wert;
	}

	public CurrencyUnit getWaehrung() {
		return euro;
	}

	public Geldbetrag increaseGeldbetrag(Geldbetrag geldbetrag) {
		return new Geldbetrag(this.wert.add(geldbetrag.getWert()));
	}

	@Override
	public String toString() {
		return "Geldbetrag{" +
				"euro=" + euro +
				", wert=" + wert +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Geldbetrag that = (Geldbetrag) o;
		return Objects.equals(euro, that.euro) && Objects.equals(wert, that.wert);
	}

	@Override
	public int hashCode() {
		return Objects.hash(euro, wert);
	}
}
