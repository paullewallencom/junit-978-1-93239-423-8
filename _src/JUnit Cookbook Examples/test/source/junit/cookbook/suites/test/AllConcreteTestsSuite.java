package junit.cookbook.suites.test;

import java.io.File;
import java.lang.reflect.Modifier;

import junit.framework.Test;
import junitx.util.DirectorySuiteBuilder;
import junitx.util.TestFilter;

public class AllConcreteTestsSuite {
    public static Test suite() throws Exception {
        TestFilter filter = new TestFilter() {
            public boolean include(Class eachTestClass) {
                boolean classIsConcrete =
                    !Modifier.isAbstract(eachTestClass.getModifiers());

                return classIsConcrete;
            }

            public boolean include(String eachTestClassName) {
                return true;
            }
        };

        DirectorySuiteBuilder builder =
            new DirectorySuiteBuilder(filter);
        return builder.suite(new File("test/classes"));
    }
}
