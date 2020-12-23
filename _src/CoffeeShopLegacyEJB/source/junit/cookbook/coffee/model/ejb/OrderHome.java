package junit.cookbook.coffee.model.ejb;

import java.rmi.RemoteException;

import javax.ejb.*;

public interface OrderHome extends EJBHome {
    OrderRemote create(Integer orderId, String customerId)
        throws CreateException, RemoteException;

    OrderRemote findByPrimaryKey(Integer orderId)
        throws FinderException, RemoteException;
}
