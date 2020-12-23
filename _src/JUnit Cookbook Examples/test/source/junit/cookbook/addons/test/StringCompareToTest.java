package junit.cookbook.addons.test;

import junitx.extensions.ComparabilityTestCase;

public class StringCompareToTest extends ComparabilityTestCase {
    public StringCompareToTest(String name) {
        super(name);
    }

    protected Comparable createLessInstance() throws Exception {
        return new String("abc");
    }

    protected Comparable createEqualInstance() throws Exception {
        return new String("abcd");
    }

    protected Comparable createGreaterInstance() throws Exception {
        return new String("abcde");
    }
}
