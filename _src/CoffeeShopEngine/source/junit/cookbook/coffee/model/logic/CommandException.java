package junit.cookbook.coffee.model.logic;

import com.diasparsoftware.javax.jms.MessagingException;

public class CommandException extends RuntimeException {
    public CommandException(String message, Exception cause) {
        super(message, cause);
    }
}
