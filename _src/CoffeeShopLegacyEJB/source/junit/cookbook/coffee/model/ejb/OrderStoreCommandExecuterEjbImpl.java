package junit.cookbook.coffee.model.ejb;

import javax.ejb.EJBException;

import junit.cookbook.coffee.data.*;

import com.diasparsoftware.store.DataStoreException;

public class OrderStoreCommandExecuterEjbImpl
    implements OrderStoreCommandExecuter {

    private OrderStoreCommandExecuter executer;

    public OrderStoreCommandExecuterEjbImpl(OrderStoreCommandExecuter executer) {
        this.executer = executer;
    }

    public void execute(
        OrderStore orderStore,
        OrderStoreCommand orderStoreCommand,
        String exceptionMessage) {

        try {
            executer.execute(
                orderStore,
                orderStoreCommand,
                exceptionMessage);
        }
        catch (DataStoreException wrap) {
            throw new EJBException(exceptionMessage, wrap);
        }
    }
}
