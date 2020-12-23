package junit.cookbook.coffee.model.ejb.test;

import java.util.Vector;

import javax.naming.*;
import javax.rmi.PortableRemoteObject;

import junit.cookbook.coffee.model.CoffeeQuantity;
import junit.cookbook.coffee.model.ejb.*;

import org.apache.cactus.ServletTestCase;

public class AddToShopcartTest extends ServletTestCase {
    public void testEmptyShopcart() throws Exception {
        Context context = new InitialContext();
        Object homeAsObject = context.lookup("ejb/ShopcartOperations");

        ShopcartOperationsHome home =
            (ShopcartOperationsHome) PortableRemoteObject.narrow(
                homeAsObject,
                ShopcartOperationsHome.class);

        Vector requestedCoffeeQuantities = new Vector() {
            {
                add(new CoffeeQuantity(2, "Sumatra"));
            }
        };

        ShopcartOperations shopcartOperations = home.create();
        shopcartOperations.addToShopcart(requestedCoffeeQuantities);

        Vector items = shopcartOperations.getShopcartItems();
        assertEquals(1, items.size());
        assertEquals(new CoffeeQuantity(2, "Sumatra"), items.get(0));
    }
}
