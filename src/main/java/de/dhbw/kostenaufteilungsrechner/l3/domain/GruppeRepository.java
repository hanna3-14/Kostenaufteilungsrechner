package de.dhbw.kostenaufteilungsrechner.l3.domain;

import java.util.Optional;

public interface GruppeRepository {

	void erstelleGruppe(Gruppe neueGruppe);

	Iterable<Gruppe> findeAlleGruppen();

	Optional<Gruppe> findeGruppeÜberName(String gruppenName);

	void aktualisiereGruppe(Gruppe gruppe);

	void entferneGruppe(String gruppenName);
}
