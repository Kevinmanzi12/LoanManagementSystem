package com.bankofkigali;

public class BusinessLoan extends LoanManager {

    private String collateralType; // specific attribute

    public BusinessLoan() {
        super();
        setLoanType("Business Loan");
        this.collateralType = "";
    }

    public BusinessLoan(String loanId, double loanAmount, double interestRate,
                        int loanDuration, String officerName, String branchLocation,
                        String collateralType) {
        super(loanId, "Business Loan", loanAmount, interestRate, loanDuration, officerName, branchLocation);
        this.collateralType = collateralType;
    }

    public String getCollateralType()              { return collateralType; }
    public void setCollateralType(String type)     { this.collateralType = type; }

    @Override
    public boolean checkEligibility(double monthlyIncome) {
        // Business loans require collateral and min income check
        if (collateralType == null || collateralType.isBlank()) return false;
        if (monthlyIncome < 500_000) return false; // min 500K RWF/month
        return super.checkEligibility(monthlyIncome);
    }

    @Override
    public double calculateInterest() {
        // Higher risk premium for business loans
        return super.calculateInterest() * 1.2;
    }

    @Override
    public void approveLoan() {
        if (collateralType == null || collateralType.isBlank()) {
            System.out.println(" Cannot approve Business Loan without collateral.");
            return;
        }
        super.approveLoan();
    }

    @Override
    public String generateLoanReport() {
        return super.generateLoanReport() +
            "Collateral Type    : " + collateralType + "\n";
    }

    @Override
    public String toString() {
        return super.toString() +
            String.format("%n  Collateral    : %s", collateralType);
    }
}
