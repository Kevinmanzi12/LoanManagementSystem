package com.bankofkigali;

public class HomeLoan extends LoanManager {

    private double propertyValue; // specific attribute

    public HomeLoan() {
        super();
        setLoanType("Home Loan");
        this.propertyValue = 0.0;
    }

    public HomeLoan(String loanId, double loanAmount, double interestRate,
                    int loanDuration, String officerName, String branchLocation,
                    double propertyValue) {
        super(loanId, "Home Loan", loanAmount, interestRate, loanDuration, officerName, branchLocation);
        this.propertyValue = propertyValue;
    }

    public double getPropertyValue()             { return propertyValue; }
    public void setPropertyValue(double value)   { this.propertyValue = value; }

    @Override
    public boolean checkEligibility(double monthlyIncome) {
        // Loan-to-value ratio must be ≤ 80 %
        if (getLoanAmount() > propertyValue * 0.80) return false;
        return super.checkEligibility(monthlyIncome);
    }

    @Override
    public double calculateInterest() {
        // Home loans get a 0.9× discount
        return super.calculateInterest() * 0.9;
    }

    @Override
    public String generateLoanReport() {
        return super.generateLoanReport() +
            String.format("Property Value     : %,.2f RWF%n", propertyValue) +
            String.format("LTV Ratio          : %.1f%%%n", (getLoanAmount() / propertyValue) * 100);
    }

    @Override
    public String toString() {
        return super.toString() +
            String.format("%n  Property Value: %,.2f RWF", propertyValue);
    }
}
