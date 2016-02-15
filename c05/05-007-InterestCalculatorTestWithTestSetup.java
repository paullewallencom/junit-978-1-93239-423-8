package junit.cookbook.testdata;

import java.io.IOException;
import java.util.ResourceBundle;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class InterestCalculatorTestWithTestSetup extends TestCase {

    private static double interestRate;
    private static double loanAmount;
    private static int loanDuration;
    static final String baseName
        = "junit.cookbook.testdata.InterestCalculatorTestWithTestSetup";

    public static Test suite() {
        TestSuite testSuite = new TestSuite(
            InterestCalculatorTestWithTestSetup.class);

        TestSetup wrapper = new TestSetup(testSuite) {
            public void setUp() throws IOException {
                ResourceBundle rb = 
                    ResourceBundle.getBundle(baseName);

                interestRate = 
                    Double.parseDouble(rb.getString("interest.rate"));

                loanAmount = Double.parseDouble(
                    rb.getString("principal.amount"));

                loanDuration = Integer.parseInt(
                    rb.getString("loan.duration.years"));
            }
        };
        
        return wrapper;
    }

    public void testInterestCalculation() {
        // fake interest calculation
    }
}