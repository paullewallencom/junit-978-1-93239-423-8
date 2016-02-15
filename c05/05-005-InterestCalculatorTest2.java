package junit.cookbook.testdata;

import java.io.IOException;
import java.util.ResourceBundle;
import junit.framework.TestCase;

public class InterestCalculatorTest2 extends TestCase {

    private double interestRate;
    private double loanAmount;
    private int loanDuration;
    static final String BASE_NAME
        = "junit.cookbook.testdata.InterestCalculatorTest2";

    public void setUp() throws IOException {
        // let ResourceBundle search the classpath and automatically
        // load the corresponding properties file
        ResourceBundle rb = ResourceBundle.getBundle(BASE_NAME);

        // use ResourceBundle.getString(String key) instead of 
        // Properties.getProperty(String key)
        interestRate = Double.parseDouble(
            rb.getString("interest.rate")));
        loanAmount = Double.parseDouble(
            rb.getString("principal.amount"));
        loanDuration = Integer.parseInt(
            rb.getString("loan.duration.years"));
    }

    public void testInterestCalculation() {
        // fake interest calculation stuff using interestRate,
        // loanAmount and loanDuration
    }
}