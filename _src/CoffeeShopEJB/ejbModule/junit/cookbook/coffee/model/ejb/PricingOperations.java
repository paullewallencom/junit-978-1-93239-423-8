package junit.cookbook.coffee.model.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

import com.diasparsoftware.java.util.Money;

public interface PricingOperations extends EJBObject {
    void setPrice(String productId, Money newPrice)
        throws RemoteException;
}
