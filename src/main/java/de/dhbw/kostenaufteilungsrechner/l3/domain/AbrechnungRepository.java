package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.Optional;
import java.util.UUID;

public interface AbrechnungRepository {

	void erstelleAbrechnung(Abrechnung neueAbrechnung);

	Iterable<Abrechnung> findeAlleAbrechnungen();

	Optional<Abrechnung> findeAbrechnungÜberID(UUID abrechnungsID);

	void aktualisiereAbrechnung(Abrechnung abrechnung);

	void entferneAbrechnung(UUID abrechungsID);
}
