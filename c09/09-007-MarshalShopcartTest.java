package junit.cookbook.coffee.model.xml.test;

import java.util.Arrays;
import junit.cookbook.coffee.display.*;
import junit.cookbook.coffee.model.*;
import org.custommonkey.xmlunit.*;
import com.diasparasoftware.java.util.Money;

public class MarshalShopcartTest extends XMLTestCase {
    private CoffeeCatalog catalog;

    protected void setUp() throws Exception {
        XMLUnit.setIgnoreWhitespace(true);
        catalog = new CoffeeCatalog() {
            public String getProductId(String coffeeName) {
                return "001";
            }

            public Money getUnitPrice(String coffeeName) {
                return Money.ZERO;
            }
        };
    }

    public void testOneItemIgnoreCatalogDetails() throws Exception {
        String expectedXml =
            "<?xml version='1.0' ?>\n"
                + "<shopcart>\n"
                + "<item id=\"762\">"
                + "<name>Sumatra</name>"
                + "<quantity>2</quantity>"
                + "<unit-price>$7.50</unit-price>"
                + "<total-price>$15.00</total-price>"
                + "</item>\n"
                + "<subtotal>$15.00</subtotal>\n"
                + "</shopcart>\n";

        ShopcartModel shopcart = new ShopcartModel();
        shopcart.addCoffeeQuantities(
            Arrays.asList(
                new Object[] { new CoffeeQuantity(2, "Sumatra")}));

        String shopcartAsXml = 
            ShopcartBean.create(shopcart, catalog).asXml();
        Diff diff = new Diff(expectedXml, shopcartAsXml);

        diff.overrideDifferenceListener(
            new IgnoreCatalogDetailsDifferenceListener());

        assertTrue(diff.toString(), diff.similar());
    }
}