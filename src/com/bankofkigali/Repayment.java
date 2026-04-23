package com.bankofkigali;

import java.time.LocalDate;

public class Repayment {

    private String paymentId;
    private LoanManager loan;
    private double amountPaid;
    private LocalDate paymentDate;
    private double remainingBalance;

    // Default constructor
    public Repayment() {
        this.paymentId = "";
        this.loan = null;
        this.amountPaid = 0.0;
        this.paymentDate = LocalDate.now();
        this.remainingBalance = 0.0;
    }

    // Parameterized constructor
    public Repayment(String paymentId, LoanManager loan,
                     double amountPaid, LocalDate paymentDate) {
        this.paymentId = paymentId;
        this.loan = loan;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.remainingBalance = loan.calculateRemainingBalance() - amountPaid;
        if (this.remainingBalance < 0) this.remainingBalance = 0;
    }

    // Getters
    public String getPaymentId()        { return paymentId; }
    public LoanManager getLoan()        { return loan; }
    public double getAmountPaid()       { return amountPaid; }
    public LocalDate getPaymentDate()   { return paymentDate; }
    public double getRemainingBalance() { return remainingBalance; }

    // Setters
    public void setPaymentId(String paymentId)         { this.paymentId = paymentId; }
    public void setLoan(LoanManager loan)              { this.loan = loan; }
    public void setAmountPaid(double amountPaid)       { this.amountPaid = amountPaid; }
    public void setPaymentDate(LocalDate paymentDate)  { this.paymentDate = paymentDate; }

    // Update remaining balance after a new payment
    public void updateRemainingBalance(double newPayment) {
        this.remainingBalance = Math.max(0, this.remainingBalance - newPayment);
        System.out.printf(" Balance updated. New remaining balance: %,.2f RWF%n", remainingBalance);
    }

    @Override
    public String toString() {
        return String.format(
            "╔══════════════════════════════════════╗%n" +
            "  Payment ID       : %s%n" +
            "  Loan ID          : %s%n" +
            "  Amount Paid (RWF): %,.2f%n" +
            "  Payment Date     : %s%n" +
            "  Remaining Balance: %,.2f RWF%n" +
            "╚══════════════════════════════════════╝",
            paymentId,
            loan != null ? loan.getLoanId() : "N/A",
            amountPaid, paymentDate, remainingBalance
        );
    }
}
