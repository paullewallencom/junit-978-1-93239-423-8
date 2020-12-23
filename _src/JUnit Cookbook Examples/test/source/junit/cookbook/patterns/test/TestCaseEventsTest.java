package junit.cookbook.patterns.test;

import java.util.*;

import junit.framework.*;

public class TestCaseEventsTest
    extends TestCase
    implements TestListener {

    private List events;
    private Exception expectedException;

    public TestCaseEventsTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        events = new ArrayList();
        expectedException = new Exception("I threw this on purpose");
    }

    public void dummyPassingTest() {
    }

    public void testPassingTestCase() throws Exception {
        final TestCase testCase =
            new TestCaseEventsTest("dummyPassingTest");

        TestResult testResult = new TestResult();
        testResult.addListener(this);

        testCase.run(testResult);

        List expectedEvents = new ArrayList();
        expectedEvents.add(
            Arrays.asList(new Object[] { "startTest", testCase }));
        expectedEvents.add(
            Arrays.asList(new Object[] { "endTest", testCase }));

        assertEquals(expectedEvents, events);
    }

    public void dummyFailingTest() {
        fail("I failed on purpose");
    }

    public void testFailingTestCase() throws Exception {
        final TestCase testCase =
            new TestCaseEventsTest("dummyFailingTest");

        TestResult testResult = new TestResult();
        testResult.addListener(this);

        testCase.run(testResult);

        List expectedEvents = new ArrayList();
        expectedEvents.add(
            Arrays.asList(new Object[] { "startTest", testCase }));

        expectedEvents.add(
            Arrays.asList(
                new Object[] {
                    "addFailure",
                    testCase,
                    "I failed on purpose" }));

        expectedEvents.add(
            Arrays.asList(new Object[] { "endTest", testCase }));

        assertEquals(expectedEvents, events);
    }

    public void dummyExceptionThrowingTest() throws Exception {
        throw expectedException;
    }

    public void testError() throws Exception {
        final TestCaseEventsTest testCase =
            new TestCaseEventsTest("dummyExceptionThrowingTest");

        TestResult testResult = new TestResult();
        testResult.addListener(this);

        testCase.run(testResult);

        List expectedEvents = new ArrayList();
        expectedEvents.add(
            Arrays.asList(new Object[] { "startTest", testCase }));

        expectedEvents.add(
            Arrays.asList(
                new Object[] {
                    "addError",
                    testCase,
                    testCase.expectedException }));

        expectedEvents.add(
            Arrays.asList(new Object[] { "endTest", testCase }));

        assertEquals(expectedEvents, events);
    }

    public void addError(Test test, Throwable throwable) {
        events.add(
            Arrays.asList(
                new Object[] { "addError", test, throwable }));
    }

    public void addFailure(Test test, AssertionFailedError failure) {
        events.add(
            Arrays.asList(
                new Object[] {
                    "addFailure",
                    test,
                    failure.getMessage()}));
    }

    public void endTest(Test test) {
        events.add(Arrays.asList(new Object[] { "endTest", test }));
    }

    public void startTest(Test test) {
        events.add(Arrays.asList(new Object[] { "startTest", test }));
    }
}
