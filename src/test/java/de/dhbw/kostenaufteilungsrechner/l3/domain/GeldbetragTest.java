package de.dhbw.kostenaufteilungsrechner.l3.domain;

import de.dhbw.kostenaufteilungsrechner.l4.abstraction.Euro;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class GeldbetragTest {

	@Test
	public void increase_geldbetrag() {

		Geldbetrag geldbetrag = new Geldbetrag(new Euro('+', 5, 0));

		Geldbetrag neuerGeldbetrag = geldbetrag.increaseGeldbetrag(new Geldbetrag(new Euro('+', 3, 0)));

		assertThat(neuerGeldbetrag.getWert()).isEqualToComparingFieldByField(new Euro('+', 8, 0));
	}

	@Test
	public void ungültiger_geldbetrag() {

		Geldbetrag geldbetrag = new Geldbetrag(new Euro('-', 5, 0));

		assertThat(geldbetrag.getWert()).isEqualToComparingFieldByField(new Euro('+', 0, 0));
	}
}
