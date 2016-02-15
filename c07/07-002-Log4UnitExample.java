package junit.cookbook.reporting.log4unit;

import junit.log4j.LoggedTestCase;

public class Log4UnitExample extends LoggedTestCase {
    public void setUp() {
        debug("** SETUP ENTERED **");
    }

    public void testConnection() {
        info("> entered " + this);
        boolean connected = false;
        info("Initiating connection to server now");
        // create Connection and set connected
        // to true if successful . . .
        connected = true;
        assertTrue(connected);
    }
}