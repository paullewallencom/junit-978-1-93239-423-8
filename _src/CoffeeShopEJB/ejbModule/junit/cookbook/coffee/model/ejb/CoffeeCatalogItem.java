package junit.cookbook.coffee.model.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface CoffeeCatalogItem extends EJBObject {
    String getProductId() throws RemoteException;
    String getCoffeeName() throws RemoteException;
    int getUnitPrice() throws RemoteException;
    void setCoffeeName(String coffeeName) throws RemoteException;
    void setUnitPrice(int unitPriceInCents) throws RemoteException;
}
