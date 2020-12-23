package junit.cookbook.essays.test;

import java.io.File;

import junit.cookbook.essays.*;
import junit.framework.TestCase;
import junitx.framework.ArrayAssert;

public class RequestPollerTest extends TestCase {
    private File[] expectedFiles =
        new File[] {
            new File("c:/unittest/tmp/file1.xml"),
            new File("c:/unittest/tmp/file2.xml"),
            new File("c:/unittest/tmp/file3.xml"),
            new File("c:/unittest/tmp/file4.xml")};

    public void testPoll() throws Exception {
        FileLister fakeFileLister = new FileLister() {
            public File[] listFiles() {
                return expectedFiles;
            }
        };

        RequestProcessor spyRequestProcessor = new RequestProcessor() {
            public void process(File[] files) {
                ArrayAssert.assertEquals(
                    "Unexpected poll values",
                    expectedFiles,
                    files);
            }
        };

        RequestPoller poller =
            new RequestPoller(fakeFileLister, spyRequestProcessor);

        poller.poll();
    }
}
