package de.dhbw.kostenaufteilungsrechner.l0.plugin;

import de.dhbw.kostenaufteilungsrechner.l1.adapters.GruppeDBAdapter;
import picocli.CommandLine;

@CommandLine.Command(name = "entferne-gruppe", description = "entfernt eine Gruppe")
public class entferneGruppe implements Runnable {

	@CommandLine.Parameters(paramLabel = "Gruppenname")
	String gruppenName;

	@Override
	public void run() {

		GruppeDBAdapter gruppeDBAdapter = new GruppeDBAdapter();

		gruppeDBAdapter.entferneGruppe(gruppenName);
	}
}

