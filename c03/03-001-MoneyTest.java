package junit.cookbook.organizing;

import junit.framework.TestCase;

public class MoneyTest extends TestCase {
    public void testDefaultAmount() {
        Money defaultMoney = new Money();
        assertEquals(0, defaultMoney.inCents());
    }
}