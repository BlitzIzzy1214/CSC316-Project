package edu.ncsu.csc316.compression.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * Tests the ReportManager class
 *
 * @author Erin Emerson ememerso
 */
public class ReportManagerTest {

	/** ReportManager to test */
	private ReportManager rm;
	
	/** Output string to test */
	private String output;
	
	/** Valid test file to compress */
	private static final String COMPRESS_FILE = "input/sample-1.txt";
	
	/** Valid test file to decompress */
	private static final String DECOMPRESS_FILE = "input/sample-2.txt";
	
	/** Invalid empty test file */
	private static final String EMPTY_FILE = "input/empty.txt";

	/**
	 * Tests compress()
	 */
	@Test
	public void testCompress() {
		try {
			rm = new ReportManager(COMPRESS_FILE);
		} catch (FileNotFoundException e) {
			fail("Should not throw exception");
		}
		
		output = rm.compress();
		assertEquals("Compressed Output {\n"
				+ "   Line 1:the dog and 1 cat 3 1 fox 3 1 snake\n"
				+ "   Line 2:3 1 fish 3 1 horse 3 1 2 3 1 4\n"
				+ "   Line 3:jumped over 1 lazy brown 2 for 1 red 5 cannot\n"
				+ "   Line 4:see that 1 4 was asleep 3 11 13 1 6\n"
				+ "   Line 5:I am not 1 5\n"
				+ "}", output);
	}

	/**
	 * Tests decompress()
	 */
	@Test
	public void testDecompress() {
		try {
			rm = new ReportManager(DECOMPRESS_FILE);
		} catch (FileNotFoundException e) {
			fail("Should not throw exception");
		}
		
		output = rm.decompress();
		assertEquals("Decompressed Output {\n"
				+ "   Line 1:the dog and the cat and the fox and the snake\n"
				+ "   Line 2:and the fish and the horse and the dog and the cat\n"
				+ "   Line 3:jumped over the lazy brown dog for the red fox cannot\n"
				+ "   Line 4:see that the cat was asleep and lazy for the snake\n"
				+ "   Line 5:I am not the fox\n"
				+ "}", output);
	}
	
	/**
	 * Tests an empty file
	 */
	@Test
	public void testEmpty() {
		try {
			rm = new ReportManager(EMPTY_FILE);
		} catch (FileNotFoundException e) {
			fail("FileNotFoundException should not have been thrown");
		}
		
		assertEquals("The provided input file has no text to compress.", rm.compress());
		assertEquals("The provided input file has no text to decompress.", rm.decompress());
	}
}
