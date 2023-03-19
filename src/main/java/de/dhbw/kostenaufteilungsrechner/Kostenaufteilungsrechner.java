package de.dhbw.kostenaufteilungsrechner;

import de.dhbw.kostenaufteilungsrechner.l0.plugin.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "Kostenaufteilungsrechner",
		version = "1.0-SNAPSHOT",
		subcommands = {
				CommandLine.HelpCommand.class,
				erstelleGruppe.class,
				zeigeGruppen.class,
				aktualisiereGruppe.class,
				entferneGruppe.class,
				erstelleEvent.class,
				zeigeEvents.class,
				aktualisiereEvent.class,
				fuegeAusgabeZuEventHinzu.class,
				entferneEvent.class,
				zeigeAbrechnungen.class,
		},
		description = "Kostenaufteilungsrechner für Advanced SWE",
		usageHelpAutoWidth = true,
		mixinStandardHelpOptions = true)
public class Kostenaufteilungsrechner {

	public static void main(String... args) {
		int exitCode = new CommandLine(new Kostenaufteilungsrechner()).execute(args);
		System.exit(exitCode);
	}
}
