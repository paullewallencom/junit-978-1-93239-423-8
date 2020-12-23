package junit.cookbook.coffee.display;

import java.util.*;

import junit.cookbook.coffee.model.*;

public class CoffeeCatalogBean {
    public SortedSet items = new TreeSet(new Comparator() {
        public int compare(Object lhsObject, Object rhsObject) {
            CoffeeCatalogItemBean lhs =
                (CoffeeCatalogItemBean) lhsObject;
            CoffeeCatalogItemBean rhs =
                (CoffeeCatalogItemBean) rhsObject;

            return lhs.coffeeName.compareTo(rhs.coffeeName);
        }
    });

    public static CoffeeCatalogBean create(CoffeeCatalog coffeeCatalog) {
        CoffeeCatalogBean bean = new CoffeeCatalogBean();

        for (Iterator i = coffeeCatalog.getItems().iterator();
            i.hasNext();
            ) {

            bean.items.add(
                CoffeeCatalogItemBean.create(
                    (CoffeeCatalogItem) i.next()));
        }

        return bean;
    }

}
