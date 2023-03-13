package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l1.adapters.GruppeDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.*;
import org.javamoney.moneta.Money;
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
		GruppeDBAdapter gruppeDBAdapter = new GruppeDBAdapter();

		Event event = eventDBAdapter.findeEventÜberID(eventID).orElse(null);
		Gruppe gruppe = gruppeDBAdapter.findeGruppeÜberName(event.getGruppenName()).orElse(null);
		List<Mitglied> gruppenMitglieder = gruppe.getMitgliederListe();

		if (beschreibung == null) {
			beschreibung = System.console().readLine("Beschreibung: ");
		}

		if (geld == null) {
			geld = System.console().readLine("Geldbetrag (in EURO): ");
		}

		Geldbetrag geldbetrag = new Geldbetrag(Money.of(Double.parseDouble(geld), "EUR"));

		if (bezahler == null) {
			for (Mitglied m : gruppenMitglieder) {
				System.out.println(m.getMitgliedsID() + " " + m.getName());
			}
			bezahler = System.console().readLine("Wer hat bezahlt? Gib die ID ein: ");
		}

		if (empfaenger == null) {
			for (Mitglied m : gruppenMitglieder) {
				System.out.println(m.getMitgliedsID() + " " + m.getName());
			}
			empfaenger = System.console().readLine("Für wen wurde bezahlt? Gib die ID(s) ein: ");
		}

		List<Integer> empfaengerIDs = new ArrayList<>();
		String[] empf = empfaenger.replaceAll(",", "").split(" ");

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
