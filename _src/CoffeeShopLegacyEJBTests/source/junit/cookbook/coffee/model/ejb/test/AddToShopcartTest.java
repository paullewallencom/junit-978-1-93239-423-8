package junit.cookbook.coffee.model.ejb.test;

import java.util.*;

import javax.ejb.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;

import junit.cookbook.coffee.model.CoffeeQuantity;
import junit.cookbook.coffee.model.ejb.*;
import junit.framework.TestCase;

import org.easymock.MockControl;
import org.mockejb.*;
import org.mockejb.jndi.*;
import org.mockejb.jndi.MockContext;

public class AddToShopcartTest
    extends TestCase
    implements LegacyShopcartHome {

    private static final String SESSION_BEAN_JNDI_NAME =
        "ejb/legacy/ShopcartOperations";
    private static final String ENTITY_BEAN_JNDI_NAME =
        "ejb/legacy/Shopcart";

    private LegacyShopcart mockShopcart;
    private MockControl shopcartControl;
    private MockContainer mockContainer;

    protected void setUp() throws Exception {
        MockContextFactory.setAsInitial();

        Context context = new InitialContext();
        context.bind(ENTITY_BEAN_JNDI_NAME, this);

        mockContainer = new MockContainer(context);

        shopcartControl =
            MockControl.createNiceControl(LegacyShopcart.class);

        mockShopcart = (LegacyShopcart) shopcartControl.getMock();

        deployLegacyShopcartOperationsEjb(mockContainer);
    }

    public void testEmptyShopcart() throws Exception {
        final String coffeeName = "Sumatra";

        mockShopcart.containsCoffeeNamed(coffeeName);
        shopcartControl.setReturnValue(false);

        mockShopcart.setQuantity(coffeeName, 1);
        shopcartControl.setVoidCallable();

        shopcartControl.replay();

        LegacyShopcartOperationsHome home =
            lookupShopcartOperationsHome();

        Vector requestedQuantities = new Vector() {
            {
                add(new CoffeeQuantity(1, coffeeName));
            }
        };

        home.create().addToShopcart(requestedQuantities);

        shopcartControl.verify();
    }

    private LegacyShopcartOperationsHome lookupShopcartOperationsHome()
        throws NamingException {

        Context rootContext = new InitialContext();

        Object homeAsObject =
            rootContext.lookup(SESSION_BEAN_JNDI_NAME);

        Object narrowedHome =
            PortableRemoteObject.narrow(
                homeAsObject,
                LegacyShopcartOperationsHome.class);

        return (LegacyShopcartOperationsHome) narrowedHome;
    }

    private void deployLegacyShopcartOperationsEjb(MockContainer mockContainer)
        throws NamingException {

        SessionBeanDescriptor shopcartOperationsBeanDescriptor =
            new SessionBeanDescriptor(
                SESSION_BEAN_JNDI_NAME,
                LegacyShopcartOperationsHome.class,
                LegacyShopcartOperations.class,
                LegacyShopcartOperationsBean.class);

        mockContainer.deploy(shopcartOperationsBeanDescriptor);
    }

    public LegacyShopcart findByUserName(String userName) {
        return mockShopcart;
    }

    public void remove(Object object)
        throws RemoveException, EJBException {

        // Intentionally do nothing
    }
}
