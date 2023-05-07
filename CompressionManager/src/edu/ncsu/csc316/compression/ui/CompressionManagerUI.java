package edu.ncsu.csc316.compression.ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.compression.manager.ReportManager;

/**
 * Starts the Compression Manager program. Calls to ReportManager to read in the
 * file input by the user. User selects compress or decompress, and the file's
 * altered contents are printed. User will be reprompted until they quit the
 * program.
 *
 * @author Erin Emerson ememerso
 */
public class CompressionManagerUI {

	/** Report manager */
	private static ReportManager rm;

	/** Command to exit the program */
	private static final String QUIT = "Quit";

	/**
	 * Prints prompts to the console telling the user what to input. Prompts for an
	 * input file and compression/decompression. If the user enters the quit
	 * command, the program will exit.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String filePath;
		boolean emptyFile = true;
		long elapsed = 0;

		System.out.print("Compression Manager. \"Quit\" to exit.");

		do {
			boolean invalidFile = true;

			do {
				System.out.print("\nEnter a file to be compressed/decompressed: ");
				filePath = in.nextLine();
				filePath = filePath.trim();
				if (filePath.equalsIgnoreCase(QUIT)) {
					System.out.println("Exiting Compression Manager.");
					in.close();
					System.exit(0);
				} else {
					try {
						long start = System.currentTimeMillis();
						rm = new ReportManager(filePath);
						long end = System.currentTimeMillis();
						elapsed = end - start;
						invalidFile = false;
					} catch (FileNotFoundException e) {
						System.out.print("Invalid file.");
					}
				}
			} while (invalidFile);

			String selection;
			char input = 'c';
			boolean invalidSelection = true;

			do {
				System.out.print("(C)ompress/(D)ecompress: ");
				selection = in.next();
				selection = selection.trim();
				if (selection.equalsIgnoreCase(QUIT)) {
					System.out.println("Exiting Compression Manager.");
					in.close();
					System.exit(0);
				} else {
					input = selection.charAt(0);
					try {
						if (input == 'c' || input == 'C' || input == 'd' || input == 'D') {
							invalidSelection = false;
						} else {
							throw new IllegalArgumentException();
						}
					} catch (IllegalArgumentException e) {
						System.out.print("Invalid selection.\n");
					}
				}
			} while (invalidSelection);

			String output;

			if (input == 'c' || input == 'C') {
				long start = System.currentTimeMillis();
				output = rm.compress();
				long end = System.currentTimeMillis();
				elapsed += end - start;
				System.out.print(output);
				System.out.print("\nRuntime: " + elapsed + " ms");
			} else {
				long start = System.currentTimeMillis();
				output = rm.decompress();
				long end = System.currentTimeMillis();
				elapsed += end - start;
				System.out.print(output);
				System.out.print("\nRuntime: " + elapsed + " ms");
			}

			in.nextLine();
		} while (emptyFile);

		in.close();
	}
}
