package junit.cookbook.testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;

public class InterestCalculatorTest extends TestCase {
    private double interestRate;
    private double loanAmount;
    private int loanDuration;
    String propertiesFile = System.getProperty("test.data.file");

    public void setUp() throws IOException {
        FileInputStream fis = new FileInputStream(propertiesFile);
        Properties p = new Properties();
        p.load(fis);

        interestRate = Double.parseDouble(
            p.getProperty("interest.rate"));
        loanAmount = Double.parseDouble(
            p.getProperty("principal.amount"));
        loanDuration = Integer.parseInt(
            p.getProperty("loan.duration.years"));
    }

    public void testInterestCalculation() {
        // fake interest calculation
    }
}