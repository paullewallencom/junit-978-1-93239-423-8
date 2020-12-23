package junit.cookbook.coffee.model.ejb;

import java.util.*;

import javax.ejb.EJBLocalObject;


public interface LegacyShopcart extends EJBLocalObject {
    Set getItems();
    boolean containsCoffeeNamed(String eachCoffeeName);
    int getQuantity(String eachCoffeeName);
    void setQuantity(String eachCoffeeName, int i);
}
