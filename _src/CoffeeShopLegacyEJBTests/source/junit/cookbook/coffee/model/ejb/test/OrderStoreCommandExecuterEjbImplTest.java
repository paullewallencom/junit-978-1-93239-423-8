package junit.cookbook.coffee.model.ejb.test;

import javax.ejb.EJBException;

import junit.cookbook.coffee.data.*;
import junit.cookbook.coffee.model.ejb.OrderStoreCommandExecuterEjbImpl;
import junit.framework.TestCase;

import org.easymock.MockControl;

import com.diasparsoftware.store.DataStoreException;

public class OrderStoreCommandExecuterEjbImplTest extends TestCase {
    private MockControl orderStoreControl;
    private OrderStore mockStore;

    public void testStoreThrowsException() throws Exception {
        final DataStoreException cause = new DataStoreException();

        OrderStoreCommand crashTestDummyCommand =
            new OrderStoreCommand() {

            public void execute(OrderStore orderStore) {
                throw cause;
            }
        };

        orderStoreControl =
            MockControl.createNiceControl(OrderStore.class);

        mockStore = (OrderStore) orderStoreControl.getMock();

        String failureMessage = "I expect failure";

        OrderStoreCommandExecuterEjbImpl executer =
            new OrderStoreCommandExecuterEjbImpl(
                new SimpleOrderStoreCommandExecuter());

        try {
            executer.execute(
                mockStore,
                crashTestDummyCommand,
                failureMessage);

            fail("Executer did not throw EJBException");
        }
        catch (EJBException expected) {
            assertTrue(
                expected.getMessage().startsWith(failureMessage));

            assertSame(cause, expected.getCausedByException());
        }
    }
}
