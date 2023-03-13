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

	public Euro add(Euro summand) {
		this.euroBetrag += summand.getEuroBetrag();
		this.centBetrag += summand.getCentBetrag();
		if (this.centBetrag >= 100) {
			this.euroBetrag += floor(this.centBetrag / 100);
			this.centBetrag -= floor(this.centBetrag / 100) * 100;
		}
		return this;
	}

	public Euro subtract(Euro subtrahend) {
		this.euroBetrag -= subtrahend.getEuroBetrag();
		this.centBetrag -= subtrahend.getCentBetrag();
		if (this.centBetrag < 0) {
			this.euroBetrag -= floor(abs(this.centBetrag / 100)) + 1;
			this.centBetrag += (floor(abs(this.centBetrag / 100)) + 1) * 100;
		}
		return this;
	}
	
	public Euro divide(int divisor) {
		int rest = 0;
		this.centBetrag += (this.euroBetrag % divisor) * 100;
		this.euroBetrag = (int) floor(this.euroBetrag / divisor);
		do {
			rest = this.centBetrag % divisor;
			this.centBetrag += 1;
		} while (rest != 0);
		this.centBetrag = (int) floor(this.centBetrag / divisor);
		return this;
	}

	@Override
	public String toString() {
		return String.format("%d,%02d€", euroBetrag, centBetrag);
	}
}
