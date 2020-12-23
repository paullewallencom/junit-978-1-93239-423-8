package junit.cookbook.coffee.model.logic;

import java.util.Collections;

import junit.cookbook.coffee.model.Order;

import com.diasparsoftware.javax.jms.*;

public class SubmitOrderCommand {
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void execute(MapMessageSender mapMessageSender) {
        try {
            mapMessageSender.sendMapMessage(
                "queue/Orders",
                Collections.singletonMap(
                    "customer-email",
                    order.customer.emailAddress));
        }
        catch (MessagingException e) {
            throw new CommandException(
                "Unable to submit order " + order,
                e);
        }
    }
}