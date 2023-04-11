package de.dhbw.kostenaufteilungsrechner.l4.abstraction;

import com.google.gson.annotations.Expose;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class Euro {

	@Expose
	private char vorzeichen;
	@Expose
	private int euroBetrag;
	@Expose
	private int centBetrag;

	public Euro(char vorzeichen, int euroBetrag, int centBetrag) {
		this.vorzeichen = vorzeichen;
		this.euroBetrag = euroBetrag;
		this.centBetrag = centBetrag;
	}

	public char getVorzeichen() {
		return vorzeichen;
	}

	public void setVorzeichen(char vorzeichen) {
		this.vorzeichen = vorzeichen;
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
		char vorzeichen = '+';
		int aInCent = 0;
		if (a.getVorzeichen() == '-') {
			aInCent = a.getEuroBetrag() * (-100) - a.getCentBetrag();
		} else {
			aInCent = a.getEuroBetrag() * 100 + a.getCentBetrag();
		}
		int bInCent = 0;
		if (b.getVorzeichen() == '-') {
			bInCent = b.getEuroBetrag() * (-100) - b.getCentBetrag();
		} else {
			bInCent = b.getEuroBetrag() * 100 + b.getCentBetrag();
		}
		int summeInCent = aInCent + bInCent;
		if (summeInCent < 0) {
			vorzeichen = '-';
		}
		Euro summe = new Euro(vorzeichen, abs(summeInCent / 100), abs(summeInCent % 100));
		return summe;
	}

	public static Euro subtract(Euro minuend, Euro subtrahend) {
		char vorzeichen = '+';
		int minuendInCent = 0;
		if (minuend.getVorzeichen() == '-') {
			minuendInCent = minuend.getEuroBetrag() * (-100) - minuend.getCentBetrag();
		} else {
			minuendInCent = minuend.getEuroBetrag() * 100 + minuend.getCentBetrag();
		}
		int subtrahendInCent = 0;
		if (subtrahend.getVorzeichen() == '-') {
			subtrahendInCent = subtrahend.getEuroBetrag() * (-100) - subtrahend.getCentBetrag();
		} else {
			subtrahendInCent = subtrahend.getEuroBetrag() * 100 + subtrahend.getCentBetrag();
		}
		int differenzInCent = minuendInCent - subtrahendInCent;
		if (differenzInCent < 0) {
			vorzeichen = '-';
		}
		Euro differenz = new Euro(vorzeichen, abs(differenzInCent / 100), abs(differenzInCent % 100));
		return differenz;
	}

	public static Euro divide(Euro dividend, int divisor) {
		char vorzeichen = '+';
		Euro quotient = new Euro(vorzeichen, dividend.getEuroBetrag(), dividend.getCentBetrag());
		int rest = 0;
		quotient.centBetrag += (dividend.getEuroBetrag() % divisor) * 100; // 100 Cent dazu
		quotient.euroBetrag = (int) floor(dividend.getEuroBetrag() / divisor);
		do {
			rest = quotient.centBetrag % divisor;
			quotient.centBetrag += 1;
		} while (rest != 0);
		quotient.centBetrag -= 1;
		quotient.centBetrag /= divisor;
		if ((dividend.getVorzeichen() == '-' && divisor > 0) || (dividend.getVorzeichen() == '+' && divisor < 0)) {
			vorzeichen = '-';
		}
		return new Euro(vorzeichen, abs(quotient.getEuroBetrag()), quotient.getCentBetrag());
	}

	@Override
	public String toString() {
		return String.format("%s%d,%02d€", vorzeichen, euroBetrag, centBetrag);
	}
}