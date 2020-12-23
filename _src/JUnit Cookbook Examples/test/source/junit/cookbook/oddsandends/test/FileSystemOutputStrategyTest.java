package junit.cookbook.oddsandends.test;

import java.io.*;

import junit.framework.*;

public class FileSystemOutputStrategyTest extends TestCase {
	private File expectedOutputFile;

	public FileSystemOutputStrategyTest(int testNumber) {
		setName("Test #" + testNumber);
	}

	protected void setUp() throws Exception {
		expectedOutputFile = new File("test/data/output.txt");
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Writing to File tests");

		for (int i = 0; i < 100; i++) {
			suite.addTest(new FileSystemOutputStrategyTest(i) {
				protected void runTest() throws Throwable {
					testWriteToFile();
				}
			});
		}

		return suite;
	}

	public void testWriteToFile() throws Exception {
		assertFalse(expectedOutputFile.exists());
		write("test/data", "output.txt", "Hello, world.");
		assertTrue(expectedOutputFile.exists());
	}

	private void write(String directory, String fileName, String text)
			throws Exception {

		FileWriter fileWriter = new FileWriter(new File(directory, fileName));
		PrintWriter out = new PrintWriter(fileWriter);
		out.println(text);
		out.flush();
		out.close();
	}

	protected void tearDown() throws Exception {
		expectedOutputFile.delete();
	}
}