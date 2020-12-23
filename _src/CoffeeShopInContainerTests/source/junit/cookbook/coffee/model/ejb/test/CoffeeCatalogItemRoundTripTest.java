package junit.cookbook.coffee.model.ejb.test;

import javax.naming.*;
import javax.rmi.PortableRemoteObject;

import junit.cookbook.coffee.model.ejb.*;

import org.apache.cactus.ServletTestCase;

public class CoffeeCatalogItemRoundTripTest extends ServletTestCase {
    public void testFindByCoffeeName() throws Exception {
        Context rootContext = new InitialContext();

        Object homeAsObject =
            rootContext.lookup("CoffeeCatalogItem");

        assertNotNull("JNDI returned null!", homeAsObject);

        CoffeeCatalogItemHome home =
            (CoffeeCatalogItemHome) PortableRemoteObject.narrow(
                homeAsObject,
                CoffeeCatalogItemHome.class);

        assertNotNull("Narrow returned null!", home);
        
        CoffeeCatalogItem item = home.findByCoffeeName("Sumatra");
        assertEquals("Sumatra", item.getCoffeeName());
        assertEquals("000", item.getProductId());
        assertEquals(750, item.getUnitPrice());
    }
}
