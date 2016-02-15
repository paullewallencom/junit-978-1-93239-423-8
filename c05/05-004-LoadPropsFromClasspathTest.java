package junit.cookbook.testdata;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.TestCase;

public class LoadPropsFromClasspathTest extends TestCase {

    String name = "/" + System.getProperty("test.data.file", 
                                           "test.properties"_;

    public void testLoadedProperties() throws IOException {
        InputStream is = this.getClass().getResourceAsStream(name);

        Properties p = new Properties();
        p.load(is);

        // We do not recommend using System.out and System.err in tests 
        // as a general practice, but here we list the properties to 
        // System.out just to demonstrate that they have been found and 
        // loaded.
        p.list(System.out);
    }
}