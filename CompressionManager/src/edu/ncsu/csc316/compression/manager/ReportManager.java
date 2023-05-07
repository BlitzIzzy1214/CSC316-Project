package edu.ncsu.csc316.compression.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Takes the file path and calls to CompressionManager to process the input and
 * compressed/decompressed map. Turns compressed/decompressed map into string to
 * be output.
 *
 * @author Erin Emerson ememerso
 */
public class ReportManager {

	/** Indent for output string */
	private static final String INDENT = "   ";

	/** Compression manager */
	private CompressionManager cm;

	/**
	 * Constructs the ReportManager. Calls CompressionManager to read file.
	 * 
	 * @param pathToInputFile path to input file to read from
	 * @throws FileNotFoundException if the file cannot be read
	 */
	public ReportManager(String pathToInputFile) throws FileNotFoundException {
		cm = new CompressionManager(pathToInputFile);
	}

	/**
	 * Calls CompressionManager.getCompressed() to compress the input contents.
	 * Creates a string of the compressed contents to be printed.
	 * 
	 * @return string of compressed file contents
	 * @throws IllegalArgumentException if input file is empty
	 */
	public String compress() {
		Map<Integer, List<String>> outputMap;
		try {
			outputMap = cm.getCompressed();
		} catch (IllegalArgumentException e) {
			return "The provided input file has no text to compress.";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("Compressed Output {\n");

//		for (Entry<Integer, List<String>> e : outputMap.entrySet()) {
		for (int i = 1; i <= outputMap.size(); i++) {
//			Entry<Integer, List<String>> e = outputMap.get(i);
			List<String> line = outputMap.get(i);
//			Integer lineNumber = i;

			sb.append(INDENT);
			sb.append("Line ");
			sb.append(i);
			sb.append(":");

			int size = line.size();
			for (int j = 0; j < size; j++) {
				sb.append(line.get(j));
				if (j < size - 1) {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}

		sb.append("}");
		String output = sb.toString();
		return output;
	}

	/**
	 * Calls CompressionManager.getDecompressed() to decompress the input contents.
	 * Creates a string of the decompressed contents to be printed.
	 * 
	 * @return string of decompressed file contents
	 * @throws IllegalArgumentException if input file is empty
	 */
	public String decompress() {
		Map<Integer, List<String>> outputMap;
		try {
			outputMap = cm.getDecompressed();
		} catch (IllegalArgumentException e) {
			return "The provided input file has no text to decompress.";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("Decompressed Output {\n");

//		for (Entry<Integer, List<String>> e : outputMap.entrySet()) {
		for (int i = 1; i <= outputMap.size(); i++) {
			List<String> line = outputMap.get(i);
//			Integer lineNumber = e.getKey();

			sb.append(INDENT);
			sb.append("Line ");
			sb.append(i);
			sb.append(":");

			int size = line.size();
			for (int j = 0; j < size; j++) {
				sb.append(line.get(j));
				if (j < size - 1) {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}

		sb.append("}");
		String output = sb.toString();
		return output;
	}
}
