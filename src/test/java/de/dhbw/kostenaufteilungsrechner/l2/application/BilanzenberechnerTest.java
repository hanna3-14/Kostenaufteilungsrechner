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

public class BilanzenberechnerTest {

	@Test
	public void bilanzenberechnerTest() {

		List<Integer> empfaengerListe = new ArrayList<>();
		empfaengerListe.add(1);
		empfaengerListe.add(2);
		empfaengerListe.add(3);

		BilanzenBerechner bilanzenBerechner = new BilanzenBerechner();

		Ausgabe ausgabe = new Ausgabe("Testausgabe", new Geldbetrag(new Euro('+', 10, 0)), 1, empfaengerListe);

		List<Integer> empfaengerListe2 = new ArrayList<>();
		empfaengerListe2.add(1);
		empfaengerListe2.add(2);
		empfaengerListe2.add(3);
		empfaengerListe2.add(4);

		Ausgabe ausgabe2 = new Ausgabe("Testausgabe2", new Geldbetrag(new Euro('+', 20, 0)), 2, empfaengerListe2);

		List<Ausgabe> ausgabenListe = new ArrayList<>();
		ausgabenListe.add(ausgabe);
		ausgabenListe.add(ausgabe2);

		Abrechnung mockedAbrechnung = mock(Abrechnung.class);
		expect(mockedAbrechnung.getAbrechnungsID()).andReturn(UUID.randomUUID());
		expect(mockedAbrechnung.getBilanzenBerechner()).andReturn(bilanzenBerechner);
		expect(mockedAbrechnung.getBilanzenMap()).andReturn(bilanzenBerechner.berechneBilanzen(ausgabenListe)).times(5);
		replay(mockedAbrechnung);

		assertThat(mockedAbrechnung.getBilanzenMap().size()).isEqualTo(4);
		assertThat(mockedAbrechnung.getBilanzenMap().get(1).getWert()).isEqualToComparingFieldByField(new Euro('+', 1, 66));
		assertThat(mockedAbrechnung.getBilanzenMap().get(2).getWert()).isEqualToComparingFieldByField(new Euro('+', 11, 66));
		assertThat(mockedAbrechnung.getBilanzenMap().get(3).getWert()).isEqualToComparingFieldByField(new Euro('-', 8, 34));
		assertThat(mockedAbrechnung.getBilanzenMap().get(4).getWert()).isEqualToComparingFieldByField(new Euro('-', 5, 0));
	}
}