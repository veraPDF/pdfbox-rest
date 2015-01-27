/**
 * 
 */
package org.verapdf.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author cfw
 *
 */
public class VeraPdfCli {
	/**
	 * The application name, used to invoke the CLI application
	 */
	public static final String APP_NAME = "veraPdf";
	
	// String constants for CLI Options
	private static final String VALIDATE_OPT = "validate";
	private static final String VALIDATE_OPT_DESC = "Request PDF/A validation";
	private static final String FILE_OPT = "file";
	private static final String FILE_OPT_ARG = "filepath";
	private static final String FILE_OPT_DESC = "Absolute path of file to validate.";
	private static final String URL_OPT = "url";
	private static final String URL_OPT_ARG = "URL";
	private static final String URL_OPT_DESC = "URI encoded URL of file to validate.";
	private static final String PDFA_OPT = "pdfa";
	private static final String PDFA_OPT_ARG = "PDF/A flavour";
	private static final String PDFA_OPT_DESC = "PDF/A flavour to use for validation, can be (none|1a|1b|2a|2b|3a|3b|3u).";
	private static final String STOPERRORS_OPT = "stoperrors";
	private static final String STOPERRORS_OPT_ARG = "number";
	private static final String STOPERRORS_OPT_DESC = "The number of errors after which validation is interupted, must be an integer greater than 0.";
	private static final String HELP_OPT = "help";
	private static final String HELP_OPT_DESC = "Print this message.";

	// Constants for default values
	private static final int STOPERRORS_DEFAULT = 0;

	// Create the options object
	private static final Options OPTIONS = new Options();
	static {
		Option help = new Option(HELP_OPT, HELP_OPT_DESC);
		Option validate = new Option(VALIDATE_OPT, VALIDATE_OPT_DESC);
		@SuppressWarnings("static-access")
		Option file = OptionBuilder.withArgName(FILE_OPT_ARG).hasArg()
				.withDescription(FILE_OPT_DESC).create(FILE_OPT);
		@SuppressWarnings("static-access")
		Option url = OptionBuilder.withArgName(URL_OPT_ARG).hasArg()
				.withDescription(URL_OPT_DESC).create(URL_OPT);
		@SuppressWarnings("static-access")
		Option pdfa = OptionBuilder.withArgName(PDFA_OPT_ARG).hasArg()
				.withDescription(PDFA_OPT_DESC).create(PDFA_OPT);
		@SuppressWarnings("static-access")
		Option stopErrors = OptionBuilder.withArgName(STOPERRORS_OPT_ARG)
				.hasArg().withDescription(STOPERRORS_OPT_DESC)
				.create(STOPERRORS_OPT);

		OPTIONS.addOption(help);
		OPTIONS.addOption(validate);
		OPTIONS.addOption(file);
		OPTIONS.addOption(url);
		OPTIONS.addOption(pdfa);
		OPTIONS.addOption(stopErrors);
	}

	/**
	 * Main CLI entry point, process the command line arguments
	 * 
	 * @param args
	 *            Java.lang.String array of command line args, to be processed
	 *            using Apache commons CLI.
	 */
	public static void main(String[] args) {
		CommandLineParser gnuParser = new GnuParser();
		try {
			CommandLine cmdLine = gnuParser.parse(OPTIONS, args);
			
			// If help requested then output help message and terminate
			if (cmdLine.hasOption(HELP_OPT) || args.length == 0) {
				outputHelpAndTerminate(0);
			}
		} catch (ParseException excep) {
			System.err.println("Command line parsing failed, exception message: " + excep.getLocalizedMessage());
			excep.printStackTrace();
			outputHelpAndTerminate(1);
		}
	}

	private final static void outputHelpAndTerminate(final int exitCode) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(APP_NAME, OPTIONS);
		System.exit(exitCode);
	}
	
}