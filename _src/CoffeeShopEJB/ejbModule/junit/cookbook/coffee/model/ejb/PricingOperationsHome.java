package junit.cookbook.coffee.model.ejb;

import java.rmi.RemoteException;

import javax.ejb.*;

public interface PricingOperationsHome extends EJBHome {
    PricingOperations create() throws RemoteException, CreateException;
}
