package junit.cookbook.coffee.deployment.test;

import java.io.FileReader;

import org.apache.xpath.XPathAPI;
import org.custommonkey.xmlunit.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class CoffeeShopControllerDeploymentTest extends XMLTestCase {
    private Document webDeploymentDescriptorDocument;
    
    protected void setUp() throws Exception {
        XMLUnit.setIgnoreWhitespace(true);

        webDeploymentDescriptorDocument =
            XMLUnit.buildTestDocument(
                new InputSource(
                    new FileReader("../CoffeeShopWeb/Web Content/WEB-INF/web.xml")));
    }
}
