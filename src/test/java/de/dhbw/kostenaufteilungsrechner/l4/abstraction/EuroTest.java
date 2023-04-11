package de.dhbw.kostenaufteilungsrechner.l4.abstraction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EuroTest {

	@Test
	public void addiere_positiv() {
		Euro summand1 = new Euro('+', 3, 76);
		Euro summand2 = new Euro('+', 5, 45);

		Euro summe = Euro.add(summand1, summand2);

		assertEquals(summe.getVorzeichen(), '+');
		assertEquals(summe.getEuroBetrag(), 9);
		assertEquals(summe.getCentBetrag(), 21);
	}

	@Test
	public void addiere_summand1_negativ() {
		Euro summand1 = new Euro('-', 3, 76);
		Euro summand2 = new Euro('+', 5, 44);

		Euro summe = Euro.add(summand1, summand2);

		assertEquals(summe.getVorzeichen(), '+');
		assertEquals(summe.getEuroBetrag(), 1);
		assertEquals(summe.getCentBetrag(), 68);
	}

	@Test
	public void addiere_negatives_ergebnis() {
		Euro summand1 = new Euro('-', 5, 44);
		Euro summand2 = new Euro('+', 3, 76);

		Euro summe = Euro.add(summand1, summand2);

		assertEquals(summe.getVorzeichen(), '-');
		assertEquals(summe.getEuroBetrag(), 1);
		assertEquals(summe.getCentBetrag(), 68);
	}

	@Test
	public void addiere_summand2_negativ() {
		Euro summand1 = new Euro('-', 5, 44);
		Euro summand2 = new Euro('-', 3, 76);

		Euro summe = Euro.add(summand1, summand2);

		assertEquals(summe.getVorzeichen(), '-');
		assertEquals(summe.getEuroBetrag(), 9);
		assertEquals(summe.getCentBetrag(), 20);
	}

	@Test
	public void subtrahiere_positiv() {
		Euro minuend = new Euro('+', 5, 10);
		Euro subtrahend = new Euro('+', 2, 50);

		Euro differenz = Euro.subtract(minuend, subtrahend);

		assertEquals(differenz.getVorzeichen(), '+');
		assertEquals(differenz.getEuroBetrag(), 2);
		assertEquals(differenz.getCentBetrag(), 60);
	}

	@Test
	public void subtrahiere_negativer_minuend() {
		Euro minuend = new Euro('-', 5, 10);
		Euro subtrahend = new Euro('+', 2, 50);

		Euro differenz = Euro.subtract(minuend, subtrahend);

		assertEquals(differenz.getVorzeichen(), '-');
		assertEquals(differenz.getEuroBetrag(), 7);
		assertEquals(differenz.getCentBetrag(), 60);
	}

	@Test
	public void subtrahriere_negatives_ergebnis() {
		Euro minuend = new Euro('+', 2, 10);
		Euro subtrahend = new Euro('+', 2, 50);

		Euro differenz = Euro.subtract(minuend, subtrahend);

		assertEquals(differenz.getVorzeichen(), '-');
		assertEquals(differenz.getEuroBetrag(), 0);
		assertEquals(differenz.getCentBetrag(), 40);
	}

	@Test
	public void subtrahiere_zwei_negative_zahlen() {
		Euro minuend = new Euro('-', 5, 10);
		Euro subtrahend = new Euro('-', 2, 50);

		Euro differenz = Euro.subtract(minuend, subtrahend);

		assertEquals(differenz.getVorzeichen(), '-');
		assertEquals(differenz.getEuroBetrag(), 2);
		assertEquals(differenz.getCentBetrag(), 60);
	}

	@Test
	public void dividiere_positiv() {
		Euro dividend = new Euro('+', 3, 0);
		int divisor = 3;

		Euro quotient = Euro.divide(dividend, divisor);

		assertEquals(quotient.getVorzeichen(), '+');
		assertEquals(quotient.getEuroBetrag(), 1);
		assertEquals(quotient.getCentBetrag(), 0);
	}

	@Test
	public void dividiere_negativer_dividend() {
		Euro dividend = new Euro('-', 3, 0);
		int divisor = 3;

		Euro quotient = Euro.divide(dividend, divisor);

		assertEquals(quotient.getVorzeichen(), '-');
		assertEquals(quotient.getEuroBetrag(), 1);
		assertEquals(quotient.getCentBetrag(), 0);
	}

	@Test
	public void dividiere_negativer_divisor() {
		Euro dividend = new Euro('+', 3, 0);
		int divisor = -3;

		Euro quotient = Euro.divide(dividend, divisor);

		assertEquals(quotient.getVorzeichen(), '-');
		assertEquals(quotient.getEuroBetrag(), 1);
		assertEquals(quotient.getCentBetrag(), 0);
	}

	@Test
	public void dividiere_zwei_negative_zahlen() {
		Euro dividend = new Euro('-', 3, 0);
		int divisor = -3;

		Euro quotient = Euro.divide(dividend, divisor);

		assertEquals(quotient.getVorzeichen(), '+');
		assertEquals(quotient.getEuroBetrag(), 1);
		assertEquals(quotient.getCentBetrag(), 0);
	}

	@Test
	public void formatierung_positiv() {
		Euro euro = new Euro('+', 3, 50);

		assertEquals(euro.toString(), "+3,50€");
	}

	@Test
	public void formatierung_negativ() {
		Euro euro = new Euro('-', 3, 50);

		assertEquals(euro.toString(), "-3,50€");
	}
}
