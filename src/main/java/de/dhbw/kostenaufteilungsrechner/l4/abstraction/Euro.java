package de.dhbw.kostenaufteilungsrechner.l4.abstraction;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class Euro {

	private int euroBetrag;
	private int centBetrag;

	public Euro(int euroBetrag, int centBetrag) {
		this.euroBetrag = euroBetrag;
		this.centBetrag = centBetrag;
	}

	public int getEuroBetrag() {
		return euroBetrag;
	}

	public void setEuroBetrag(int euroBetrag) {
		this.euroBetrag = euroBetrag;
	}

	public int getCentBetrag() {
		return centBetrag;
	}

	public void setCentBetrag(int centBetrag) {
		this.centBetrag = centBetrag;
	}

	public static Euro add(Euro a, Euro b) {
		int summeInCent = 0;
		if (a.getEuroBetrag() < 0) {
			summeInCent = a.getEuroBetrag() * 100 - a.getCentBetrag() + b.getEuroBetrag() * 100 + b.getCentBetrag();
		} else {
			summeInCent = a.getEuroBetrag() * 100 + a.getCentBetrag() + b.getEuroBetrag() * 100 + b.getCentBetrag();
		}
		Euro summe = new Euro(summeInCent / 100, abs(summeInCent % 100));
		return summe;
	}

	public static Euro subtract(Euro minuend, Euro subtrahend) {
		int minuendInCent = 0;
		if (minuend.getEuroBetrag() < 0) {
			minuendInCent = minuend.getEuroBetrag() * 100 - minuend.getCentBetrag();
		} else {
			minuendInCent = minuend.getEuroBetrag() * 100 + minuend.getCentBetrag();
		}
		int subtrahendInCent = subtrahend.getEuroBetrag() * 100 + subtrahend.getCentBetrag();
		int differenzInCent = minuendInCent - subtrahendInCent;
		Euro differenz = new Euro(differenzInCent / 100, abs(differenzInCent % 100));
		return differenz;
	}

	public static Euro divide(Euro dividend, int divisor) {
		Euro quotient = new Euro(dividend.getEuroBetrag(), dividend.getCentBetrag());
		int rest = 0;
		quotient.centBetrag += (dividend.getEuroBetrag() % divisor) * 100; // 100 Cent dazu
		quotient.euroBetrag = (int) floor(dividend.getEuroBetrag() / divisor);
		do {
			rest = quotient.centBetrag % divisor;
			quotient.centBetrag += 1;
		} while (rest != 0);
		quotient.centBetrag -= 1;
		quotient.centBetrag /= divisor;
		return new Euro(quotient.getEuroBetrag(), quotient.getCentBetrag());
	}

	@Override
	public String toString() {
		return String.format("%d,%02d€", euroBetrag, centBetrag);
	}
}
