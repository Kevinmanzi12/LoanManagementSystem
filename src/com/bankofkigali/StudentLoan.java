package com.bankofkigali;

public class StudentLoan extends LoanManager {

    private String universityName; // specific attribute

    public StudentLoan() {
        super();
        setLoanType("Student Loan");
        this.universityName = "";
    }

    public StudentLoan(String loanId, double loanAmount, double interestRate,
                       int loanDuration, String officerName, String branchLocation,
                       String universityName) {
        super(loanId, "Student Loan", loanAmount, interestRate, loanDuration, officerName, branchLocation);
        this.universityName = universityName;
    }

    public String getUniversityName()              { return universityName; }
    public void setUniversityName(String uniName)  { this.universityName = uniName; }

    @Override
    public boolean checkEligibility(double monthlyIncome) {
        // Student loans: max 5M RWF and university must be specified
        if (getLoanAmount() > 5_000_000) return false;
        if (universityName == null || universityName.isBlank()) return false;
        return true; // income check relaxed for students
    }

    @Override
    public double calculateInterest() {
        // Subsidised: 50 % of normal interest
        return super.calculateInterest() * 0.5;
    }

    @Override
    public double calculateMonthlyInstallment() {
        // Grace period: repayment starts 6 months after loan (effective duration reduced)
        int effectiveDuration = getLoanDuration() - 6;
        if (effectiveDuration <= 0) effectiveDuration = 1;
        double r = getInterestRate() / 100.0 / 12.0;
        if (r == 0) return calculateTotalRepayment() / effectiveDuration;
        return calculateTotalRepayment() * (r * Math.pow(1 + r, effectiveDuration))
                / (Math.pow(1 + r, effectiveDuration) - 1);
    }

    @Override
    public String generateLoanReport() {
        return super.generateLoanReport() +
            "University         : " + universityName + "\n";
    }

    @Override
    public String toString() {
        return super.toString() +
            String.format("%n  University    : %s", universityName);
    }
}
