package junit.cookbook.coffee.service;

import java.io.IOException;


public class ServiceException extends RuntimeException {
    public ServiceException(String message, Exception cause) {
        super(message, cause);
    }
}
