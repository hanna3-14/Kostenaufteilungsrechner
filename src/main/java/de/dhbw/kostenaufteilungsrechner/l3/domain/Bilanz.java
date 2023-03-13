package de.dhbw.kostenaufteilungsrechner.l3.domain;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Objects;

public final class Bilanz {

	private CurrencyUnit euro = Monetary.getCurrency("EUR");
	private Money wert;

	public Bilanz(Money wert) {
		this.wert = wert;
	}

	public Money getWert() {
		return wert;
	}

	public CurrencyUnit getWaehrung() {
		return euro;
	}

	public Bilanz increaseBilanz(Geldbetrag geldbetrag) {
		return new Bilanz(this.getWert().add(geldbetrag.getWert()));
	}

	public Bilanz decreaseBilanz(Geldbetrag geldbetrag) {
		return new Bilanz(this.getWert().subtract(geldbetrag.getWert()));
	}

	@Override
	public String toString() {
		return wert + " " + euro;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Bilanz bilanz = (Bilanz) o;
		return Objects.equals(euro, bilanz.euro) && Objects.equals(wert, bilanz.wert);
	}

	@Override
	public int hashCode() {
		return Objects.hash(euro, wert);
	}
}
