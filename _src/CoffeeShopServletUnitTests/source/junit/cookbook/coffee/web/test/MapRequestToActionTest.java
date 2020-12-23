package junit.cookbook.coffee.web.test;

import java.util.*;
import java.util.regex.*;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

import junit.cookbook.coffee.HttpServletRequestToActionMapper;
import junit.framework.TestCase;

import org.apache.catalina.connector.HttpRequestBase;

import com.diasparsoftware.java.util.*;
import com.diasparsoftware.javax.servlet.http.*;

public class MapRequestToActionTest extends TestCase {
    private HttpServletRequestToActionMapper actionMapper;

    protected void setUp() throws Exception {
        actionMapper = new HttpServletRequestToActionMapper();
    }

    public void testBrowseCatalogAction() throws Exception {
        Map parameters =
            Collections.singletonMap(
                "browseCatalog",
                new String[] { "catalog" });

        doTestMapAction(
            "Browse Catalog",
            "/coffeeShop/coffee",
            parameters);
    }

    public void testAddToShopcart() throws Exception {
        HashMap parameters = new HashMap() {
            {
                put("addToShopcart-18", new String[] { "Buy!" });
                put("quantity-18", new String[] { "5" });
            }
        };

        doTestMapAction(
            "Add to Shopcart",
            "/coffeeShop/coffee",
            parameters);
    }

    private void doTestMapAction(
        String expectedActionName,
        String uri,
        Map parameters) {

        HttpServletRequest request =
            HttpUtil.makeRequestIgnoreSession(uri, parameters);

        assertEquals(
            expectedActionName,
            actionMapper.getActionName(request));
    }
}
