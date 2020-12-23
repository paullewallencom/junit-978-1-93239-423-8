package junit.cookbook.coffee.web.test;

import java.io.*;

import junit.extensions.TestSetup;
import junit.framework.*;

import org.custommonkey.xmlunit.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public abstract class StrutsConfigFixture extends XMLTestCase {
    protected String getActionForwardPathXpath(
        String action,
        String forward) {

        return getActionXpath(action)
            + "/forward[@name='"
            + forward
            + "']/@path";
    }

    protected String getActionXpath(String path) {
        return "/struts-config/action-mappings/action[@path='"
            + path
            + "']";
    }

    protected String getActionForwardXpath(String action) {
        return getActionXpath(action) + "/forward";
    }
}
