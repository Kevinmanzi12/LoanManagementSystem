package com.bankofkigali;

public class PersonalLoan extends LoanManager {

    private String purposeOfLoan; // specific attribute

    public PersonalLoan() {
        super();
        setLoanType("Personal Loan");
        this.purposeOfLoan = "";
    }

    public PersonalLoan(String loanId, double loanAmount, double interestRate,
                        int loanDuration, String officerName, String branchLocation,
                        String purposeOfLoan) {
        super(loanId, "Personal Loan", loanAmount, interestRate, loanDuration, officerName, branchLocation);
        this.purposeOfLoan = purposeOfLoan;
    }

    public String getPurposeOfLoan()              { return purposeOfLoan; }
    public void setPurposeOfLoan(String purpose)  { this.purposeOfLoan = purpose; }

    @Override
    public boolean checkEligibility(double monthlyIncome) {
        // Personal loans: max amount = 10× monthly income
        if (getLoanAmount() > monthlyIncome * 10) return false;
        return super.checkEligibility(monthlyIncome);
    }

    @Override
    public double calculateInterest() {
        // Personal loans carry a 1.5× premium on interest
        return super.calculateInterest() * 1.5;
    }

    @Override
    public String generateLoanReport() {
        return super.generateLoanReport() +
            "Purpose of Loan    : " + purposeOfLoan + "\n";
    }

    @Override
    public String toString() {
        return super.toString() +
            String.format("%n  Purpose       : %s", purposeOfLoan);
    }
}
