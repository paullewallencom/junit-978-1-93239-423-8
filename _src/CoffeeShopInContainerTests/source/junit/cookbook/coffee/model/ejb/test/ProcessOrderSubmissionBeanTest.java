package junit.cookbook.coffee.model.ejb.test;

import java.util.*;

import javax.management.ObjectName;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;

import org.apache.cactus.ServletTestCase;
import org.jboss.jmx.adaptor.rmi.RMIAdaptor;

import com.diasparsoftware.javax.jms.*;
import com.dumbster.smtp.SimpleSmtpServer;

public class ProcessOrderSubmissionBeanTest extends ServletTestCase {
    private MapMessageSender messageSender;
    private SimpleSmtpServer smtpServer;

    protected void setUp() throws Exception {
        messageSender = new JBossMapMessageSender();
        smtpServer = SimpleSmtpServer.start();
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        smtpServer.stop();
    }

    public void testHappyPath() throws Exception {
        Context context = new InitialContext();
        Object object = context.lookup("jmx/invoker/RMIAdaptor");
        RMIAdaptor rmiAdaptor =
            (RMIAdaptor) PortableRemoteObject.narrow(
                object,
                RMIAdaptor.class);

        ObjectName mailServiceObjectName =
            new ObjectName("jboss:service=Mail");

        Object configurationAttribute =
            rmiAdaptor.getAttribute(
                mailServiceObjectName,
                "Configuration");
       
        assertNotNull(configurationAttribute);
        
        fail(configurationAttribute.getClass().getName());
        

        String orderQueueJndiName = "queue/Orders";

        Map messageContent =
            Collections.singletonMap(
                "customer-email",
                "jbr@diasparsoftware.com");

        messageSender.sendMapMessage(
            orderQueueJndiName,
            messageContent);

        Thread.sleep(500);

    }
}
