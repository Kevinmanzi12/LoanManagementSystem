package com.bankofkigali;

public class AgriculturalLoan extends LoanManager {

    private double farmSizeHectares; // specific attribute

    public AgriculturalLoan() {
        super();
        setLoanType("Agricultural Loan");
        this.farmSizeHectares = 0.0;
    }

    public AgriculturalLoan(String loanId, double loanAmount, double interestRate,
                            int loanDuration, String officerName, String branchLocation,
                            double farmSizeHectares) {
        super(loanId, "Agricultural Loan", loanAmount, interestRate, loanDuration, officerName, branchLocation);
        this.farmSizeHectares = farmSizeHectares;
    }

    public double getFarmSizeHectares()             { return farmSizeHectares; }
    public void setFarmSizeHectares(double size)    { this.farmSizeHectares = size; }

    @Override
    public boolean checkEligibility(double monthlyIncome) {
        // Must have at least 0.5 hectares
        if (farmSizeHectares < 0.5) return false;
        return super.checkEligibility(monthlyIncome);
    }

    @Override
    public double calculateInterest() {
        // Government-subsidised agricultural rate: 0.7×
        return super.calculateInterest() * 0.7;
    }

    @Override
    public void approveLoan() {
        System.out.println("Agricultural Loan approval includes government subsidy check.");
        super.approveLoan();
    }

    @Override
    public String generateLoanReport() {
        return super.generateLoanReport() +
            String.format("Farm Size          : %.2f hectares%n", farmSizeHectares);
    }

    @Override
    public String toString() {
        return super.toString() +
            String.format("%n  Farm Size     : %.2f hectares", farmSizeHectares);
    }
}
