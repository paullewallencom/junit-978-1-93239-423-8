package junit.cookbook.coffee.endtoend.test;

import java.io.IOException;
import java.net.*;

import junit.framework.TestCase;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

public class ViewCatalogTest extends TestCase {
    private WebClient webClient;

    protected void setUp() throws Exception {
        webClient = new WebClient();
    }

    public void testWelcomePage() throws Exception {
        Page welcomePage = navigateToWelcomePage();

        WelcomeHtmlPage welcomeHtmlPage =
            new WelcomeHtmlPage(welcomePage);
        assertEquals("Welcome!", welcomeHtmlPage.getTitleText());
        assertTrue(welcomeHtmlPage.containsLaunchPoints());
        assertTrue(welcomeHtmlPage.canBrowseToCatalog());
    }

    public void testBrowseCatalog() throws Exception {
        Page catalogPage = navigateToCatalogPage();
        CatalogHtmlPage catalogHtmlPage =
            new CatalogHtmlPage(catalogPage);

        assertTrue(catalogHtmlPage.containsCatalog());
    }

    public void testAddNothingToShopcart() throws Exception {
        HtmlPage catalogPage = navigateToCatalogPage();
        CatalogHtmlPage catalogHtmlPage =
            new CatalogHtmlPage(catalogPage);

        assertTrue(catalogHtmlPage.catalogHasItems());
        catalogHtmlPage.getFirstProductBuyButton().click();
    }

    protected Page navigateToWelcomePage()
        throws IOException, MalformedURLException {

        return webClient.getPage(
            new URL(getServerUrl() + "/coffeeShop"));
    }

    protected HtmlPage navigateToCatalogPage()
        throws IOException, MalformedURLException, Exception {
        WelcomeHtmlPage welcomeHtmlPage =
            new WelcomeHtmlPage(navigateToWelcomePage());

        Page catalogPage = welcomeHtmlPage.clickBrowseCatalog();
        return (HtmlPage) catalogPage;
    }

    protected String getServerUrl() {
        return "http://localhost:8080";
    }

}
