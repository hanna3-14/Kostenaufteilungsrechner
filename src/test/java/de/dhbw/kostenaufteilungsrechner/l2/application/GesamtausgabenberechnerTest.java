package de.dhbw.kostenaufteilungsrechner.l2.application;

import de.dhbw.kostenaufteilungsrechner.l3.domain.Abrechnung;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Ausgabe;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Geldbetrag;
import de.dhbw.kostenaufteilungsrechner.l4.abstraction.Euro;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.easymock.EasyMock.*;

public class GesamtausgabenberechnerTest {

	@Test
	public void gesamtausgabenberechnerTest() {

		List<Integer> empfaengerListe = new ArrayList<>();
		empfaengerListe.add(1);
		empfaengerListe.add(2);
		empfaengerListe.add(3);
		empfaengerListe.add(4);

		GesamtausgabenBerechner gesamtausgabenBerechner = new GesamtausgabenBerechner();

		Ausgabe ausgabe = new Ausgabe("Testausgabe", new Geldbetrag(new Euro('+', 10, 0)), 1, empfaengerListe);
		Ausgabe ausgabe2 = new Ausgabe("Testausgabe2", new Geldbetrag(new Euro('+', 20, 0)), 2, empfaengerListe);

		List<Ausgabe> ausgabenListe = new ArrayList<>();
		ausgabenListe.add(ausgabe);
		ausgabenListe.add(ausgabe2);

		Abrechnung mockedAbrechnung = mock(Abrechnung.class);
		expect(mockedAbrechnung.getAbrechnungsID()).andReturn(UUID.randomUUID());
		expect(mockedAbrechnung.getGesamtausgabenBerechner()).andReturn(gesamtausgabenBerechner);
		expect(mockedAbrechnung.getGesamtausgaben()).andReturn(gesamtausgabenBerechner.berechneGesamtausgaben(ausgabenListe));
		replay(mockedAbrechnung);

		assertThat(mockedAbrechnung.getGesamtausgaben().getWert()).isEqualToComparingFieldByField(new Euro('+', 30, 0));
	}
}