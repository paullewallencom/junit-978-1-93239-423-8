package junit.cookbook.coffee.model.ejb;

import javax.jms.*;

import junit.cookbook.coffee.model.logic.ProcessOrderSubmissionAction;
import junit.cookbook.coffee.service.*;

public class ProcessOrderSubmissionMessageHandler {
    private ProcessOrderSubmissionAction action =
        new ProcessOrderSubmissionAction();

    public void handleMessage(Message message, MailService mailService)
        throws JMSException {

        MapMessage incomingMessage = (MapMessage) message;

        String customerEmailAddress =
            incomingMessage.getString("customer-email");

        action.processOrder(mailService, customerEmailAddress);
    }
}
