package com.bankofkigali;

import java.time.LocalDate;

public class LoanManager extends Loan implements Payable {

    private String officerName;
    private String branchLocation;

    // Track payments for Payable
    protected double amountPaid;
    protected String lastReceiptDetails;

    // Default constructor
    public LoanManager() {
        super();
        this.officerName = "";
        this.branchLocation = "";
        this.amountPaid = 0.0;
        this.lastReceiptDetails = "";
    }

    // Parameterized constructor
    public LoanManager(String loanId, String loanType, double loanAmount,
                       double interestRate, int loanDuration,
                       String officerName, String branchLocation) {
        super(loanId, loanType, loanAmount, interestRate, loanDuration);
        this.officerName = officerName;
        this.branchLocation = branchLocation;
        this.amountPaid = 0.0;
        this.lastReceiptDetails = "";
    }

    // Getters & Setters
    public String getOfficerName()    { return officerName; }
    public String getBranchLocation() { return branchLocation; }
    public double getAmountPaid()     { return amountPaid; }

    public void setOfficerName(String officerName)       { this.officerName = officerName; }
    public void setBranchLocation(String branchLocation) { this.branchLocation = branchLocation; }

    // ── Loan abstract method implementations ─────────────────────────────────

    @Override
    public double calculateInterest() {
        // Simple interest: P × R × T
        return getLoanAmount() * (getInterestRate() / 100) * (getLoanDuration() / 12.0);
    }

    @Override
    public double calculateMonthlyInstallment() {
        double r = getInterestRate() / 100.0 / 12.0; // monthly rate
        int n = getLoanDuration();
        if (r == 0) return getLoanAmount() / n;
        // Standard amortisation formula
        return getLoanAmount() * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    }

    @Override
    public boolean checkEligibility(double monthlyIncome) {
        double installment = calculateMonthlyInstallment();
        // Debt-to-income ratio must be ≤ 40 %
        return (installment / monthlyIncome) <= 0.40;
    }

    @Override
    public void approveLoan() {
        setLoanStatus("APPROVED");
        System.out.println("Loan [" + getLoanId() + "] has been APPROVED by " + officerName);
    }

    @Override
    public void rejectLoan(String reason) {
        setLoanStatus("REJECTED");
        System.out.println(" Loan [" + getLoanId() + "] has been REJECTED. Reason: " + reason);
    }

    @Override
    public double calculateTotalRepayment() {
        return getLoanAmount() + calculateInterest();
    }

    @Override
    public String generateLoanReport() {
        return String.format(
            "%n=== LOAN REPORT ===%n" +
            "Loan ID            : %s%n" +
            "Loan Type          : %s%n" +
            "Principal (RWF)    : %,.2f%n" +
            "Interest Rate      : %.2f%%%n" +
            "Duration           : %d months%n" +
            "Total Interest     : %,.2f RWF%n" +
            "Total Repayment    : %,.2f RWF%n" +
            "Monthly Installment: %,.2f RWF%n" +
            "Status             : %s%n" +
            "Officer            : %s%n" +
            "Branch             : %s%n" +
            "===================%n",
            getLoanId(), getLoanType(), getLoanAmount(),
            getInterestRate(), getLoanDuration(),
            calculateInterest(), calculateTotalRepayment(),
            calculateMonthlyInstallment(), getLoanStatus(),
            officerName, branchLocation
        );
    }

    @Override
    public boolean validateLoanDetails() {
        if (getLoanId() == null || getLoanId().isBlank()) return false;
        if (getLoanAmount() <= 0) return false;
        if (getInterestRate() < 0 || getInterestRate() > 100) return false;
        if (getLoanDuration() <= 0) return false;
        return true;
    }

    // ── Payable interface implementations ────────────────────────────────────

    @Override
    public void processPayment(double amount) {
        if (amount <= 0) {
            System.out.println(" Payment amount must be positive.");
            return;
        }
        double remaining = calculateRemainingBalance();
        if (amount > remaining) {
            System.out.println(" Payment exceeds remaining balance. Adjusted to: " + String.format("%,.2f", remaining));
            amount = remaining;
        }
        amountPaid += amount;
        lastReceiptDetails = generatePaymentReceipt();
        System.out.println("Payment of RWF " + String.format("%,.2f", amount) + " processed successfully.");
    }

    @Override
    public double calculateRemainingBalance() {
        return Math.max(0, calculateTotalRepayment() - amountPaid);
    }

    @Override
    public String generatePaymentReceipt() {
        return String.format(
            "%n------- PAYMENT RECEIPT -------%n" +
            "Date             : %s%n" +
            "Loan ID          : %s%n" +
            "Total Loan Cost  : %,.2f RWF%n" +
            "Amount Paid      : %,.2f RWF%n" +
            "Remaining Balance: %,.2f RWF%n" +
            "Branch           : %s%n" +
            "-------------------------------%n",
            LocalDate.now(), getLoanId(),
            calculateTotalRepayment(), amountPaid,
            calculateRemainingBalance(), branchLocation
        );
    }

    // ── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return super.toString() +
            String.format("%n  Officer       : %s%n  Branch        : %s",
                officerName, branchLocation);
    }
}
