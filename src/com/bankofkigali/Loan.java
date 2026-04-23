package com.bankofkigali;

public abstract class Loan {

    // Private attributes (Encapsulation)
    private String loanId;
    private String loanType;
    private double loanAmount;
    private double interestRate;
    private int loanDuration; // months
    private String loanStatus;

    // Default constructor
    public Loan() {
        this.loanId = "";
        this.loanType = "";
        this.loanAmount = 0.0;
        this.interestRate = 0.0;
        this.loanDuration = 0;
        this.loanStatus = "PENDING";
    }

    // Parameterized constructor
    public Loan(String loanId, String loanType, double loanAmount,
                double interestRate, int loanDuration) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.loanDuration = loanDuration;
        this.loanStatus = "PENDING";
    }

    // Getters
    public String getLoanId()       { return loanId; }
    public String getLoanType()     { return loanType; }
    public double getLoanAmount()   { return loanAmount; }
    public double getInterestRate() { return interestRate; }
    public int getLoanDuration()    { return loanDuration; }
    public String getLoanStatus()   { return loanStatus; }

    // Setters
    public void setLoanId(String loanId)           { this.loanId = loanId; }
    public void setLoanType(String loanType)       { this.loanType = loanType; }
    public void setLoanAmount(double loanAmount)   { this.loanAmount = loanAmount; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
    public void setLoanDuration(int loanDuration)  { this.loanDuration = loanDuration; }
    public void setLoanStatus(String loanStatus)   { this.loanStatus = loanStatus; }

    // ── Abstract Methods (8) ──────────────────────────────────────────────────

    public abstract double calculateInterest();

    public abstract double calculateMonthlyInstallment();

    public abstract boolean checkEligibility(double monthlyIncome);

    public abstract void approveLoan();

    public abstract void rejectLoan(String reason);

    public abstract double calculateTotalRepayment();

    public abstract String generateLoanReport();

    public abstract boolean validateLoanDetails();

    // ── toString ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format(
            "╔══════════════════════════════════════╗%n" +
            "  Loan ID       : %s%n" +
            "  Loan Type     : %s%n" +
            "  Amount (RWF)  : %,.2f%n" +
            "  Interest Rate : %.2f%%%n" +
            "  Duration      : %d months%n" +
            "  Status        : %s%n" +
            "╚══════════════════════════════════════╝",
            loanId, loanType, loanAmount, interestRate, loanDuration, loanStatus
        );
    }
}
