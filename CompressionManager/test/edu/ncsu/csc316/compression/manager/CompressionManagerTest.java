package edu.ncsu.csc316.compression.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Iterator;

import org.junit.Test;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Tests the CompressionManager class
 *
 * @author Erin Emerson ememerso
 */
public class CompressionManagerTest {

	/** CompressionManager to test */
	private CompressionManager cm;
	
	/** Output map to test */
	private Map<Integer, List<String>> output;
	
	/** Valid test file to compress */
	private static final String COMPRESS_FILE = "input/sample-1.txt";
	
	/** Valid test file to decompress */
	private static final String DECOMPRESS_FILE = "input/sample-2.txt";
	
	/** Invalid empty test file */
	private static final String EMPTY_FILE = "input/empty.txt";

	/**
	 * Tests getCompressed()
	 */
	@Test
	public void testGetCompressed() {
		try {
			cm = new CompressionManager(COMPRESS_FILE);
		} catch (FileNotFoundException e) {
			fail("Should not throw exception");
		}
		
		output = cm.getCompressed();
		
		Iterator<Entry<Integer, List<String>>> it = output.entrySet().iterator();
		assertTrue(it.hasNext());
		Entry<Integer, List<String>> e = it.next();
		Iterator<String> strings = e.getValue().iterator();

		assertEquals(1, (int) e.getKey());
		assertTrue(strings.hasNext());
		assertEquals("the", strings.next());
		assertEquals("dog", strings.next());
		assertEquals("and", strings.next());
		assertEquals("1", strings.next());
		assertEquals("cat", strings.next());
		assertEquals("3", strings.next());
		assertEquals("1", strings.next());
		assertEquals("fox", strings.next());
		assertEquals("3", strings.next());
		assertEquals("1", strings.next());
		assertEquals("snake", strings.next());
		assertFalse(strings.hasNext());
		
		assertTrue(it.hasNext());
		e = it.next();
		strings = e.getValue().iterator();

		assertEquals(2, (int) e.getKey());
		assertTrue(strings.hasNext());
		assertEquals("3", strings.next());
		assertEquals("1", strings.next());
		assertEquals("fish", strings.next());
		assertEquals("3", strings.next());
		assertEquals("1", strings.next());
		assertEquals("horse", strings.next());
		assertEquals("3", strings.next());
		assertEquals("1", strings.next());
		assertEquals("2", strings.next());
		assertEquals("3", strings.next());
		assertEquals("1", strings.next());
		assertEquals("4", strings.next());
		assertFalse(strings.hasNext());
		
		assertTrue(it.hasNext());
		e = it.next();
		strings = e.getValue().iterator();

		assertEquals(3, (int) e.getKey());
		assertTrue(strings.hasNext());
		assertEquals("jumped", strings.next());
		assertEquals("over", strings.next());
		assertEquals("1", strings.next());
		assertEquals("lazy", strings.next());
		assertEquals("brown", strings.next());
		assertEquals("2", strings.next());
		assertEquals("for", strings.next());
		assertEquals("1", strings.next());
		assertEquals("red", strings.next());
		assertEquals("5", strings.next());
		assertEquals("cannot", strings.next());
		assertFalse(strings.hasNext());
		
		assertTrue(it.hasNext());
		e = it.next();
		strings = e.getValue().iterator();

		assertEquals(4, (int) e.getKey());
		assertTrue(strings.hasNext());
		assertEquals("see", strings.next());
		assertEquals("that", strings.next());
		assertEquals("1", strings.next());
		assertEquals("4", strings.next());
		assertEquals("was", strings.next());
		assertEquals("asleep", strings.next());
		assertEquals("3", strings.next());
		assertEquals("11", strings.next());
		assertEquals("13", strings.next());
		assertEquals("1", strings.next());
		assertEquals("6", strings.next());
		assertFalse(strings.hasNext());
		
		assertTrue(it.hasNext());
		e = it.next();
		strings = e.getValue().iterator();

		assertEquals(5, (int) e.getKey());
		assertTrue(strings.hasNext());
		assertEquals("I", strings.next());
		assertEquals("am", strings.next());
		assertEquals("not", strings.next());
		assertEquals("1", strings.next());
		assertEquals("5", strings.next());
		assertFalse(strings.hasNext());
		
		assertFalse(it.hasNext());
	}

	/**
	 * Tests getDecompressed()
	 */
	@Test
	public void testGetDecompressed() {
		try {
			cm = new CompressionManager(DECOMPRESS_FILE);
		} catch (FileNotFoundException e) {
			fail("Should not throw exception");
		}
		
		output = cm.getDecompressed();
		
		Iterator<Entry<Integer, List<String>>> it = output.entrySet().iterator();
		assertTrue(it.hasNext());
		Entry<Integer, List<String>> e = it.next();
		Iterator<String> strings = e.getValue().iterator();

		assertEquals(1, (int) e.getKey());
		assertTrue(strings.hasNext());
		assertEquals("the", strings.next());
		assertEquals("dog", strings.next());
		assertEquals("and", strings.next());
		assertEquals("the", strings.next());
		assertEquals("cat", strings.next());
		assertEquals("and", strings.next());
		assertEquals("the", strings.next());
		assertEquals("fox", strings.next());
		assertEquals("and", strings.next());
		assertEquals("the", strings.next());
		assertEquals("snake", strings.next());
		assertFalse(strings.hasNext());
		
		assertTrue(it.hasNext());
		e = it.next();
		strings = e.getValue().iterator();

		assertEquals(2, (int) e.getKey());
		assertTrue(strings.hasNext());
		assertEquals("and", strings.next());
		assertEquals("the", strings.next());
		assertEquals("fish", strings.next());
		assertEquals("and", strings.next());
		assertEquals("the", strings.next());
		assertEquals("horse", strings.next());
		assertEquals("and", strings.next());
		assertEquals("the", strings.next());
		assertEquals("dog", strings.next());
		assertEquals("and", strings.next());
		assertEquals("the", strings.next());
		assertEquals("cat", strings.next());
		assertFalse(strings.hasNext());
		
		assertTrue(it.hasNext());
		e = it.next();
		strings = e.getValue().iterator();

		assertEquals(3, (int) e.getKey());
		assertTrue(strings.hasNext());
		assertEquals("jumped", strings.next());
		assertEquals("over", strings.next());
		assertEquals("the", strings.next());
		assertEquals("lazy", strings.next());
		assertEquals("brown", strings.next());
		assertEquals("dog", strings.next());
		assertEquals("for", strings.next());
		assertEquals("the", strings.next());
		assertEquals("red", strings.next());
		assertEquals("fox", strings.next());
		assertEquals("cannot", strings.next());
		assertFalse(strings.hasNext());
		
		assertTrue(it.hasNext());
		e = it.next();
		strings = e.getValue().iterator();

		assertEquals(4, (int) e.getKey());
		assertTrue(strings.hasNext());
		assertEquals("see", strings.next());
		assertEquals("that", strings.next());
		assertEquals("the", strings.next());
		assertEquals("cat", strings.next());
		assertEquals("was", strings.next());
		assertEquals("asleep", strings.next());
		assertEquals("and", strings.next());
		assertEquals("lazy", strings.next());
		assertEquals("for", strings.next());
		assertEquals("the", strings.next());
		assertEquals("snake", strings.next());
		assertFalse(strings.hasNext());
		
		assertTrue(it.hasNext());
		e = it.next();
		strings = e.getValue().iterator();

		assertEquals(5, (int) e.getKey());
		assertTrue(strings.hasNext());
		assertEquals("I", strings.next());
		assertEquals("am", strings.next());
		assertEquals("not", strings.next());
		assertEquals("the", strings.next());
		assertEquals("fox", strings.next());
		assertFalse(strings.hasNext());
		
		assertFalse(it.hasNext());
	}
	
	/**
	 * Tests an empty file
	 */
	@Test
	public void testEmpty() {
		try {
			cm = new CompressionManager(EMPTY_FILE);
		} catch (FileNotFoundException e) {
			fail("FileNotFoundException should not have been thrown");
		}
		
		assertThrows(IllegalArgumentException.class, () -> cm.getCompressed());
		assertThrows(IllegalArgumentException.class, () -> cm.getDecompressed());
	}
}
