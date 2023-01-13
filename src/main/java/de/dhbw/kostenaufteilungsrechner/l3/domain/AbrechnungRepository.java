package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.Optional;
import java.util.UUID;

public interface AbrechnungRepository {

	void erstelleAbrechnung(Abrechnung neueAbrechnung);

	Iterable<Abrechnung> findeAlleAbrechnungen() throws Exception;

	Optional<Abrechnung> findeAbrechnungÜberID(UUID abrechnungsID) throws Exception;

	void aktualisiereAbrechnung(Abrechnung abrechnung);

	void enferneAbrechnung(UUID abrechungsID);
}
