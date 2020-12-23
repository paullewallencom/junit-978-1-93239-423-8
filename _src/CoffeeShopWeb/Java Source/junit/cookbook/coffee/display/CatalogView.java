package junit.cookbook.coffee.display;

import javax.servlet.http.HttpServletRequest;

import junit.cookbook.coffee.model.CoffeeCatalog;

public class CatalogView {
    private HttpServletRequest request;

    public CatalogView(HttpServletRequest request) {
        this.request = request;
    }

    public void setCatalog(CoffeeCatalog catalog) {
        request.setAttribute(
            "catalog",
            CoffeeCatalogBean.create(catalog));
    }

    public String getUri() {
        return "catalog.jsp";
    }

    public String getLocationName() {
        return "Catalog";
    }
}
