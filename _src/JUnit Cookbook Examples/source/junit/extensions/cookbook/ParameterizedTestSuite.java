package junit.extensions.cookbook;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Vector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * A <code>TestSuite</code> that allows parameters to be
 * passed to the constructor of the <code>TestCase</code>
 * objects to support parameterized tests.
 * 
 * It seems that this is a generalization of <code>TestSuite</code>
 * and perhaps could be joined with <code>TestSuite</code> to
 * provide a slightly more general solution without compromising
 * its ease of use. Ultimately, it would be nice to push
 * <code>addParameterizedTests</code> up into <code>TestSuite</code>
 * and offer the user the choice of the two.
 * 
 * @author  <a href="mailto:jbr@diasparsoftware.com">J. B. Rainsberger</a>
 * @version  1.0
 */
public class ParameterizedTestSuite extends TestSuite {
    public ParameterizedTestSuite(String name) {
        super(name);
    }

    // TODO  Factor out the duplication between this method and TestSuite.addTestSuite
    public void addParameterizedTests(
        Class testCaseClass,
        Class[] testParameterTypes,
        Object[] testParameters) {

        setName(testCaseClass.getName());
        Constructor testCaseConstructor;

        try {
            testCaseConstructor =
                testCaseClass.getConstructor(
                    testParameterTypes);
            // Avoid generating multiple error messages
        }
        catch (NoSuchMethodException e) {
            addTest(
                warning(
                    "Class "
                        + testCaseClass.getName()
                        + " has no public constructor matching parameter types "
                        + Arrays.asList(testParameterTypes)));
            return;
        }

        if (!Modifier
            .isPublic(testCaseClass.getModifiers())) {
            addTest(
                warning(
                    "Class "
                        + testCaseClass.getName()
                        + " is not public"));
            return;
        }

        Class superClass = testCaseClass;
        Vector names = new Vector();
        while (Test.class.isAssignableFrom(superClass)) {
            Method[] methods =
                superClass.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                addTestMethod(
                    methods[i],
                    names,
                    testCaseConstructor,
                    testParameters);
            }
            superClass = superClass.getSuperclass();
        }

        if (isEmpty())
            addTest(
                warning(
                    "No tests found in "
                        + testCaseClass.getName()));
    }

    private boolean isEmpty() {
        // TODO  Push up to TestSuite to reveal intent
        return testCount() == 0;
    }

    /**
     * Returns a test which will fail and log a warning message.
     */
    private static Test warning(final String message) {
        // TODO  Make this protected in TestSuite
        return new TestCase("warning") {
            protected void runTest() {
                fail(message);
            }
        };
    }

    private void addTestMethod(
        Method testMethod,
        Vector names,
        Constructor testCaseConstructor,
        Object[] testCaseConstructorParameters) {

        // TODO  Factor out duplication with TestSuite.addTestMethod()
        String name = testMethod.getName();
        if (names.contains(name))
            return;
        if (!isPublicTestMethod(testMethod)) {
            if (isTestMethod(testMethod))
                addTest(
                    warning(
                        "Test method isn't public: "
                            + testMethod.getName()));
            return;
        }

        names.addElement(name);
        addTest(
            createTest(
                testCaseConstructor,
                testCaseConstructorParameters,
                name));
    }

    private boolean isPublicTestMethod(Method m) {
        // TODO  Make protected in TestSuite
        return isTestMethod(m)
            && Modifier.isPublic(m.getModifiers());
    }

    private boolean isTestMethod(Method m) {
        // TODO  Make protected in TestSuite
        String name = m.getName();
        Class[] parameters = m.getParameterTypes();
        Class returnType = m.getReturnType();
        return parameters.length == 0
            && name.startsWith("test")
            && returnType.equals(Void.TYPE);
    }

    static public Test createTest(
        Constructor testCaseConstructor,
        Object[] testCaseConstructorParameters,
        String testMethodName) {

        // TODO  Factor out duplication with TestSuite.createTest()
        Object test;
        try {
            test =
                testCaseConstructor.newInstance(
                    testCaseConstructorParameters);

            ((TestCase) test).setName(testMethodName);
        }
        catch (InstantiationException e) {
            return (
                warning(
                    "Cannot instantiate test case: "
                        + testMethodName
                        + " ("
                        + exceptionToString(e)
                        + ")"));
        }
        catch (InvocationTargetException e) {
            return (
                warning(
                    "Exception in constructor: "
                        + testMethodName
                        + " ("
                        + exceptionToString(
                            e.getTargetException())
                        + ")"));
        }
        catch (IllegalAccessException e) {
            return (
                warning(
                    "Cannot access test case: "
                        + testMethodName
                        + " ("
                        + exceptionToString(e)
                        + ")"));
        }

        return (Test) test;
    }

    private static String exceptionToString(Throwable t) {
        // TODO  Make protected in TestSuite
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        return stringWriter.toString();

    }

    public void addParameterizedTests(
        Class testCaseClass,
        MethodParameter[] testFixtureParameters) {

        Class[] parameterTypes =
            new Class[testFixtureParameters.length];
        Object[] parameters =
            new Object[testFixtureParameters.length];

        for (int i = 0;
            i < testFixtureParameters.length;
            i++) {
            parameterTypes[i] =
                testFixtureParameters[i].getType();
            parameters[i] =
                testFixtureParameters[i].getValue();
        }

        addParameterizedTests(
            testCaseClass,
            parameterTypes,
            parameters);
    }
}
