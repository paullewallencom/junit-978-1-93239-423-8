package junit.cookbook.testdata;

import junit.extensions.ConfigurableTestCase;

public class InterestCalculatorConfigurableTest
    extends ConfigurableTestCase {

    private static double interestRate;
    private static double loanAmount;
    private static int loanDuration;

    public InterestCalculatorConfigurableTest(String name) {
        super(name);
    }

    public void setUp() {
        interestRate = getDouble("interest.rate");
        loanAmount = getDouble("principal.amount");
        loanDuration = getInteger("loan.duration.years");
    }

    public void testInterestCalculation() {
        // fake interest calculation stuff using interestRate,
        // loan amount and loanDuration
    }
}