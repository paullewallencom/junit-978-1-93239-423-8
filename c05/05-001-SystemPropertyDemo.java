package junit.cookbook.testdata;

import junit.framework.TestCase;

public class SystemPropertyDemo extends TestCase {
    private String fooProperty = System.getProperty("FOO");
    private String spacesProperty = System.getProperty("spaces.are.ok");

    public void testSystemProperty() {
        if (fooProperty == null) {
            fail("Expected 'FOO' to be set as a system property.");
        } else {
            assertEquals("BAR", System.getProperty("FOO"));
        }
    }
}