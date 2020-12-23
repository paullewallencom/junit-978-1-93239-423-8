package junit.cookbook.coffee.test;

import java.util.*;

import javax.servlet.http.*;

import junit.cookbook.coffee.CoffeeShopController;
import junit.cookbook.coffee.model.*;
import junit.cookbook.coffee.model.logic.AddToShopcartCommand;

import com.diasparsoftware.java.util.*;
import com.diasparsoftware.javax.servlet.http.HttpSessionAdapter;
import com.meterware.httpunit.*;
import com.meterware.servletunit.*;

public class AddToShopcartParametersTest
    extends CoffeeShopControllerFixture {

    public void testCreatesCommand_ServletUnit() throws Exception {
        ServletRunner servletRunner =
            new ServletRunner(getWebDeploymentDescriptorPath());

        servletRunner.registerServlet(
            "Coffee Shop Controller",
            CoffeeShopController.class.getName());

        ServletUnitClient client = servletRunner.newClient();

        String requestUrl = "http://localhost/coffee";

        Properties requestProperties = new Properties();
        requestProperties.setProperty("quantity-0", "2");
        requestProperties.setProperty("addToShopcart-0", "Buy!");

        ShopcartModel shopcartModel = new ShopcartModel();

        WebRequest webRequest =
            getWebRequest(requestUrl, requestProperties);

        InvocationContext invocationContext =
            client.newInvocation(webRequest);

        HttpServletRequest httpRequest = invocationContext.getRequest();
        httpRequest.getSession(true).setAttribute(
            "shopcartModel",
            shopcartModel);

        CoffeeShopController controller =
            (CoffeeShopController) invocationContext.getServlet();

        CoffeeShopModel coffeeShopModel = new CoffeeShopModel();
        coffeeShopModel.getCatalog().addCoffee(
            "0",
            "Sumatra",
            Money.dollars(7, 50));

        controller.setModel(coffeeShopModel);

        AddToShopcartCommand command =
            controller.makeAddToShopcartCommand(httpRequest);

        assertEquals(
            new AddToShopcartCommand(
                new CoffeeQuantity(2, "Sumatra"),
                shopcartModel),
            command);
    }

    private WebRequest getWebRequest(
        String requestUrl,
        Properties requestProperties)
        throws Exception {

        final WebRequest request = new PostMethodWebRequest(requestUrl);

        CollectionUtil
            .forEachDo(requestProperties, new MapEntryClosure() {
            public void eachMapEntry(Object key, Object value) {
                request.setParameter((String) key, (String) value);
            }
        });

        return request;
    }
}