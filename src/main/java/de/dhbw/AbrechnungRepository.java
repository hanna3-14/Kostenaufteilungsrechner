package de.dhbw;

import java.util.UUID;

public interface AbrechnungRepository {

	void speichereAbrechnung(Abrechnung neueAbrechnung);

	Iterable<Abrechnung> findeAlleAbrechnungen() throws Exception;

	Abrechnung findeAbrechnungÜberID(UUID abrechnungsID) throws Exception;

	void aktualisiereAbrechnung(Abrechnung abrechnung);

	void loescheAbrechnung(UUID abrechungsID);
}
