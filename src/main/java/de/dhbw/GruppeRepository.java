package de.dhbw;

public interface GruppeRepository {

	void speichereGruppe(Gruppe neueGruppe);

	Iterable<Gruppe> findeAlleGruppen() throws Exception;

	Gruppe findeGruppeÜberName(String gruppenName) throws Exception;

	void aktualisiereGruppe(Gruppe gruppe);

	void loescheGruppe(String gruppenName);
}
