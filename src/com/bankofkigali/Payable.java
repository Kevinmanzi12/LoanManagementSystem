package com.bankofkigali;

public interface Payable {

    /**
     * Process a payment toward the loan.
     * @param amount the amount being paid
     */
    void processPayment(double amount);

    /**
     * Calculate the remaining balance after payments.
     * @return remaining balance in RWF
     */
    double calculateRemainingBalance();

    /**
     * Generate a text receipt for the most recent payment.
     * @return formatted receipt string
     */
    String generatePaymentReceipt();
}
