package junit.cookbook.coffee.model.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface OrderRemote extends EJBObject {
    String getCustomerId() throws RemoteException;
    void setCustomerId(String customerId) throws RemoteException;
}
