import junit.framework.Test;

import com.gargoylesoftware.base.testing.RecursiveTestSuite;
import com.gargoylesoftware.base.testing.TestFilter;

public class AllTests {
    public static Test suite() throws Exception {
        return new RecursiveTestSuite("classes", new TestFilter() {
            public boolean accept(Class eachTestClass) {
                return true;
            }
        });
    }
}