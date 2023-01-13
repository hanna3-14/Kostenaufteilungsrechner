package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.Optional;

public interface GruppeRepository {

	void erstelleGruppe(Gruppe neueGruppe);

	Iterable<Gruppe> findeAlleGruppen() throws Exception;

	Optional<Gruppe> findeGruppeÜberName(String gruppenName) throws Exception;

	void aktualisiereGruppe(Gruppe gruppe);

	void entferneGruppe(String gruppenName);
}
