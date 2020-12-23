package junit.cookbook.coffee.model.ejb.test;

import java.rmi.ServerException;

import javax.ejb.EJBException;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;

import junit.cookbook.coffee.model.ejb.*;

import org.apache.cactus.*;
import org.apache.cactus.client.authentication.BasicAuthentication;

import com.diasparsoftware.java.util.Money;

public class PricingOperationsSecurityTest extends ServletTestCase {
    private PricingOperationsHome home;

    protected void setUp() throws Exception {
        Context context = new InitialContext();
        Object object = context.lookup("ejb/PricingOperations");
        home =
            (PricingOperationsHome) PortableRemoteObject.narrow(
                object,
                PricingOperationsHome.class);
    }

    public void testNoCredentials() throws Exception {
        doTestExpectingSecurityException(
            "No credentials and you got through?!",
            "principal=null");
    }

    public void beginAdministrator(WebRequest request) {
        request.setRedirectorName("ServletRedirectorSecure");
        request.setAuthentication(
            new BasicAuthentication("admin", "adm1n"));
    }

    public void testAdministrator() throws Exception {
        PricingOperations pricingOperations = home.create();
        pricingOperations.setPrice("762", Money.dollars(12, 50));
    }

    public void beginShopper(WebRequest request) {
        request.setRedirectorName("ServletRedirectorSecure");
        request.setAuthentication(
            new BasicAuthentication("shopper", "sh0pper"));
    }

    public void testShopper() throws Exception {
        doTestExpectingSecurityException(
            "Only administrators should be allowed to do this!",
            "Insufficient method permissions");
    }

    private void doTestExpectingSecurityException(
        String testFailureMessage,
        String expectedSecurityExceptionMessageContains)
        throws Exception {

        try {
            PricingOperations pricingOperations = home.create();
            fail(testFailureMessage);
        }
        catch (ServerException expected) {
            Throwable serverExceptionCause = expected.getCause();
            assertTrue(
                "This caused the ServerException: "
                    + serverExceptionCause,
                serverExceptionCause instanceof EJBException);

            EJBException ejbException =
                (EJBException) serverExceptionCause;

            Exception ejbExceptionCause =
                ejbException.getCausedByException();

            assertTrue(
                "This caused the EJBException: " + ejbExceptionCause,
                ejbExceptionCause instanceof SecurityException);

            SecurityException securityException =
                (SecurityException) ejbExceptionCause;

            String securityExceptionMessage =
                securityException.getMessage();
            
            assertTrue(
                securityExceptionMessage,
                securityExceptionMessage.matches(
                    ".*"
                        + expectedSecurityExceptionMessageContains
                        + ".*"));
        }
    }
}
