package junit.cookbook.coffee.display;

import junit.cookbook.coffee.model.CoffeeCatalogItem;

public class CoffeeCatalogItemBean {
    public String productId;
    public String coffeeName;
    public String unitPrice;

    public CoffeeCatalogItemBean(
        String productId,
        String coffeeName,
        String unitPrice) {

        this.productId = productId;
        this.coffeeName = coffeeName;
        this.unitPrice = unitPrice;
    }

    public static CoffeeCatalogItemBean create(CoffeeCatalogItem item) {
        return new CoffeeCatalogItemBean(
            item.productId,
            item.coffeeName,
            item.unitPrice.toString());
    }
}
