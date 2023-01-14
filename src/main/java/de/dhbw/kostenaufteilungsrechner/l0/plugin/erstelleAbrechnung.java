package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.AbrechnungDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l1.adapters.EventDBAdapter;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Abrechnung;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Event;
import picocli.CommandLine;

import java.util.UUID;

@CommandLine.Command(name = "erstelle-abrechnung", description = "erstellt eine Abrechnung für ein Event")
public class erstelleAbrechnung implements Runnable {

	@CommandLine.Parameters(paramLabel = "eventID")
	UUID eventID;

	@Override
	public void run() {

		AbrechnungDBAdapter abrechnungDBAdapter = new AbrechnungDBAdapter();

		EventDBAdapter eventDBAdapter = new EventDBAdapter();

		Event event = eventDBAdapter.findeEventÜberID(eventID).orElse(null);

		if(event==null){
			System.out.println("Falsche eventID");
			return;
		}
		Abrechnung abrechnung = new Abrechnung(eventID);
		abrechnung.berechneGesamtausgaben(event);
		abrechnung.berechneBilanzen(event);

		System.out.println("Die folgende Abrechnung wurde erstellt:");
		System.out.println(abrechnung);

		abrechnungDBAdapter.erstelleAbrechnung(abrechnung);
	}
}