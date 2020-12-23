package junit.cookbook.gsbase.test;

import junit.cookbook.util.Money;
import junit.framework.TestCase;
import junitx.runner.TestRunner;

import com.gargoylesoftware.base.testing.TestUtil;

public class CloneTest extends TestCase {
    public void testCloneMoney() throws Exception {
        Money moneyToClone = Money.dollars(1000);
        TestUtil.testClone(moneyToClone, true);
    }

    public static void main(String[] args) {
        runTestsByClass(CloneTest.class);
    }

    private static void runTestsByClass(Class testClass) {
        TestRunner.main(
            new String[] {
                "-class",
                testClass.getName(),
                "-runner.properties",
                "./test/properties/runner.properties"});
    }
}
