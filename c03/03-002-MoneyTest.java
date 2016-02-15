package junit.cookbook.organizing.test;

import junit.cookbook.util.Money;
import junit.framework.TestCase;

public class MoneyTest extends TestCase {
    public void testAdd() {
        Money addend = new Money(12,50);
        Money augend = new Money(12,50);
        Money sum = addend.add(augend);
        assertEquals(2500, sum.inCents());
    }

    public void testNegate() {
        Money money = new Money(12, 50);
        Money opposite = money.negate();
        assertEquals(-1250, opposite.inCents());
    }

    public void testRound() {
        Money money = new Money(12, 50);
        Money rounded = money.roundToNearestDollar();
        assertEquals(1300, rounded.inCents());
    }
}