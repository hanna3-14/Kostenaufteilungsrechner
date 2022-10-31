package de.dhbw;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "Kostenaufteilungsrechner",
		version = "1.0-SNAPSHOT",
		subcommands = {CommandLine.HelpCommand.class},
		description = "Kostenaufteilungsrechner für Advanced SWE",
		mixinStandardHelpOptions = true)
public class Kostenaufteilungsrechner {

	public static void main(String... args) {
		int exitCode = new CommandLine(new Kostenaufteilungsrechner()).execute(args);
		System.exit(exitCode);
	}
}
