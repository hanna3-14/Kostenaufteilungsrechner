package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Ausgabe;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Geldbetrag;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CommandLine.Command(name = "fuege-ausgabe-zu-event", description = "fügt Ausgabe zu Event hinzu")
public class fuegeAusgabeZuEventHinzu implements Runnable {

	@CommandLine.Parameters(paramLabel = "eventID")
	UUID eventID;

	@CommandLine.Option(names = {"-b", "--beschreibung"}, description = "Beschreibungstext der Ausgabe")
	String beschreibung;

	@CommandLine.Option(names = {"-g", "--geldbetrag"}, description = "Geldbetrag der Ausgabe (in EURO)")
	String geld;

	@CommandLine.Option(names = {"-be", "--bezahler"}, description = "Bezahler")
	String bezahler;

	@CommandLine.Option(names = {"-e", "--empfaenger"}, description = "Empfänger")
	String empfaenger;

	@Override
	public void run() {

		EventDBAdapter eventDBAdapter = new EventDBAdapter();

		if (beschreibung == null) {
			beschreibung = System.console().readLine("Beschreibung: ");
		}

		if (geld == null) {
			geld = System.console().readLine("Geldbetrag (in EURO): ");
		}

		Geldbetrag geldbetrag = new Geldbetrag(Double.parseDouble(geld));

		if (bezahler == null) {
			bezahler = System.console().readLine("Bezahler: ");
		}

		if (empfaenger == null) {
			empfaenger = System.console().readLine("Empfänger: ");
		}

		List<Integer> empfaengerIDs = new ArrayList<>();
		String[] empf = empfaenger.split(" ");

		for (String s : empf) {
			empfaengerIDs.add(Integer.parseInt(s));
		}

		Ausgabe ausgabe = new Ausgabe(beschreibung, geldbetrag, Integer.parseInt(bezahler), empfaengerIDs);

		eventDBAdapter.fügeNeueAusgabeHinzu(eventID, ausgabe);

		System.out.println();
		System.out.println("Die folgende Ausgabe wurde erstellt und zum event " + eventID + " hinzugefügt:");
		System.out.println(ausgabe);
	}
}
