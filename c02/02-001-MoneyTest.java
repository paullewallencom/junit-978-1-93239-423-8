package junit.cookbook.common.test;

import junit.cookbook.util.Money;
import junit.framework.TestCase;
import com.gargoylesoftware.base.testing.EqualsTester;

public class MoneyTest extends TestCase {
    public void testEquals() {
        Money a = new Money(100,0);
        Money b = new Money(100,0);
        Money c = new Money(50,0);
        Object d = null;

        new EqualsTester(a,b,c,d);
    }
}