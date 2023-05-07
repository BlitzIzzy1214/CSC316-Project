package edu.ncsu.csc316.compression.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.compression.dsa.DSAFactory;
import edu.ncsu.csc316.compression.dsa.DataStructure;
import edu.ncsu.csc316.compression.io.InputReader;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Reads the input from a file to be used in compression/decompression. Given
 * user input, compresses/decompresses the file's contents, and returns the
 * updated file contents.
 *
 * @author Erin Emerson ememerso
 */
public class CompressionManager {

	/** Map from input file */
	private Map<Integer, List<String>> inputMap;

	/**
	 * Constructs the CompressionManager. Reads the input from a file to be used in
	 * compression/decompression. Sets the map and list types to be used for input,
	 * output, String list, compressed words, and decompressed words.
	 * 
	 * @param pathToInputFile path to input file to read from
	 * @throws FileNotFoundException if the file cannot be read
	 */
	public CompressionManager(String pathToInputFile) throws FileNotFoundException {
		DSAFactory.setMapType(DataStructure.UNORDEREDLINKEDMAP);
		DSAFactory.setListType(DataStructure.SINGLYLINKEDLIST);
		inputMap = InputReader.readFile(pathToInputFile);
//		DSAFactory.setMapType(DataStructure.UNORDEREDLINKEDMAP);
//		DSAFactory.setListType(DataStructure.SINGLYLINKEDLIST);
	}

	/**
	 * Compresses the file's contents, storing each unique word with a number
	 * representative of its order in the file. After one instance of a unique word,
	 * changes each previously stored word to its number value in the output map.
	 * 
	 * @return map of compressed file contents
	 * @throws IllegalArgumentException if input file is empty
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, List<String>> getCompressed() {
		if (inputMap.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		Entry<Integer, List<String>>[] entries = (Entry<Integer,List<String>>[]) (new Entry[inputMap.size()]);
		
//		for (int i = inputMap.size(); i > 0; i--) {
//			inputMap.get(i);
//		}
		for (Entry<Integer, List<String>> e : inputMap.entrySet()) {
			entries[e.getKey() - 1] = e;
		}

		Map<String, Integer> compMap = DSAFactory.getMap(null);
		Map<Integer, List<String>> outputMap = DSAFactory.getMap(null);

//		for (Entry<Integer, List<String>> e : inputMap.entrySet()) {
		for (int i = 0; i < inputMap.size(); i++) {
			Entry<Integer, List<String>> e  = entries[i];
			List<String> line = e.getValue();
			List<String> outputLine = DSAFactory.getIndexedList();
			int size = line.size();
			for (int j = 0; j < size; j++) {
				String current = line.get(j);
				if (compMap.isEmpty()) {
					outputLine.add(j, current);
					compMap.put(current, 1);
				} else {
					Integer val = compMap.get(current);
					if (val == null) {
						outputLine.add(j, current);
						compMap.put(current, compMap.size() + 1);
					} else {
						outputLine.add(j, val.toString());
					}
				}
			}
			outputMap.put(e.getKey(), outputLine);
		}

//		if (outputMap instanceof UnorderedLinkedMap) {
		 	for (int i = outputMap.size(); i > 0; i--) {
//		 		outputMap.put(i, entries[i - 1].getValue());
				outputMap.get(i);
		 	}
//		}

		return outputMap;
	}

	/**
	 * Decompresses the file's contents, storing each unique word with a number
	 * representative of its order in the file. For each number value processed in
	 * the input, changes it to its associated word in the output map.
	 * 
	 * @return map of decompressed file contents
	 * @throws IllegalArgumentException if input file is empty
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, List<String>> getDecompressed() {
		if (inputMap.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
//		Item<Integer, String>[] items = new Item<Integer, String>[inputMap.size()];
		Entry<Integer, List<String>>[] entries = (Entry<Integer,List<String>>[]) (new Entry[inputMap.size()]);
//		for (int i = inputMap.size(); i > 0; i--) {
//			inputMap.get(i);
//		}
		for (Entry<Integer, List<String>> e : inputMap.entrySet()) {
			entries[e.getKey() - 1] = e;
		}

		Map<Integer, String> decompMap = DSAFactory.getMap(null);
		Map<Integer, List<String>> outputMap = DSAFactory.getMap(null);

//		for (Entry<Integer, List<String>> e : inputMap.entrySet()) {
		for (int i = 0; i < inputMap.size(); i++) {
			Entry<Integer, List<String>> e  = entries[i];
			List<String> line = e.getValue();
			List<String> outputLine = DSAFactory.getIndexedList();
			int size = line.size();
			for (int j = 0; j < size; j++) {
				String current = line.get(j);
				try {
					Integer num = Integer.parseInt(current);
					String decomp = decompMap.get(num);
					outputLine.add(j, decomp);
				} catch (NumberFormatException n) {
					if (decompMap.isEmpty()) {
						outputLine.add(j, current);
						decompMap.put(1, current);
					} else {
						outputLine.add(j, current);
						decompMap.put(decompMap.size() + 1, current);
					}
				}
			}
			outputMap.put(e.getKey(), outputLine);
		}

//		if (outputMap instanceof UnorderedLinkedMap) {
			for (int i = outputMap.size(); i > 0; i--) {
//				outputMap.put(i, entries[i - 1].getValue());
				outputMap.get(i);
			}
//		}

		return outputMap;
	}
	/**
	private static class Item<K, V> implements Comparable<Entry<K, V>> {

		private Entry<Integer, List<String>> entry;
		
		
		public Item(Entry<Integer, List<String>> entry) {
			this.entry = entry;
		}

		public Entry<Integer, List<String>> getEntry() {
			return entry;
		}

		
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Entry<K, V> o) {
			return ((Comparable<K>) entry.getKey()).compareTo(o.getKey());
		}
		
	}
*/
}
