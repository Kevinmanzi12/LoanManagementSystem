package com.bankofkigali;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LoanSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<LoanManager> loans       = new ArrayList<>();
    private static final List<Customer>    customers   = new ArrayList<>();
    private static final List<Repayment>   repayments  = new ArrayList<>();
    private static int repaymentCounter = 1;

    public static void main(String[] args) {
        printBanner();

        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": registerCustomer();   break;
                case "2": applyForLoan();        break;
                case "3": makePayment();         break;
                case "4": viewAllLoans();        break;
                case "5": viewAllCustomers();    break;
                case "6": viewRepaymentHistory();break;
                case "7": viewLoanReport();      break;
                case "0":
                    System.out.println("\n Thank you for using Bank of Kigali LMS. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number from the menu.");
            }
        }
        scanner.close();
    }

    // ══════════════════════════════════════════════════════════════════════════
    // MENU DISPLAY
    // ══════════════════════════════════════════════════════════════════════════

    private static void printBanner() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║       BANK OF KIGALI – LOAN MANAGEMENT SYSTEM   ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private static void printMainMenu() {
        System.out.println("\n┌──────────────────────────────────┐");
        System.out.println("│            MAIN MENU             │");
        System.out.println("├──────────────────────────────────┤");
        System.out.println("│  1. Register New Customer        │");
        System.out.println("│  2. Apply for a Loan             │");
        System.out.println("│  3. Make a Loan Payment          │");
        System.out.println("│  4. View All Loans               │");
        System.out.println("│  5. View All Customers           │");
        System.out.println("│  6. View Repayment History       │");
        System.out.println("│  7. View Full Loan Report        │");
        System.out.println("│  0. Exit                         │");
        System.out.println("└──────────────────────────────────┘");
        System.out.print("Enter your choice: ");
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 1. REGISTER CUSTOMER
    // ══════════════════════════════════════════════════════════════════════════

    private static void registerCustomer() {
        System.out.println("\n─── Register New Customer ───");

        // Customer ID
        String customerId = "";
        while (true) {
            System.out.print("Customer ID (format CU-XXXX, e.g. CU-0001): ");
            customerId = scanner.nextLine().trim();
            if (InputValidator.isValidCustomerIdFormat(customerId)
                    && InputValidator.isUniqueCustomerId(customerId)) break;
        }

        // Name
        String name = "";
        while (true) {
            System.out.print("Full Name: ");
            name = scanner.nextLine().trim();
            if (InputValidator.isNonEmpty(name, "Customer Name")) break;
        }

        // National ID
        String nationalId = "";
        while (true) {
            System.out.print("National ID (16 digits): ");
            nationalId = scanner.nextLine().trim();
            if (InputValidator.isValidNationalId(nationalId)
                    && InputValidator.isUniqueNationalId(nationalId)) break;
        }

        // Phone
        String phone = "";
        while (true) {
            System.out.print("Phone Number (07XXXXXXXX or +2507XXXXXXXX): ");
            phone = scanner.nextLine().trim();
            if (InputValidator.isValidPhoneNumber(phone)) break;
        }

        Customer customer = new Customer(customerId, name, nationalId, phone);
        customers.add(customer);
        System.out.println("\n Customer registered successfully!");
        System.out.println(customer);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 2. APPLY FOR LOAN
    // ══════════════════════════════════════════════════════════════════════════

    private static void applyForLoan() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered yet. Please register a customer first.");
            return;
        }

        System.out.println("\n─── Apply for a Loan ───");

        // Loan ID
        String loanId = "";
        while (true) {
            System.out.print("Loan ID (format LN-YYYY-NNNN, e.g. LN-2024-0001): ");
            loanId = scanner.nextLine().trim();
            if (InputValidator.isValidLoanIdFormat(loanId)
                    && InputValidator.isUniqueLoanId(loanId)) break;
        }

        // Loan Type
        String loanType = "";
        while (true) {
            System.out.print("Loan Type (PERSONAL / HOME / CAR / BUSINESS / STUDENT / AGRICULTURAL): ");
            loanType = scanner.nextLine().trim();
            if (InputValidator.isValidLoanType(loanType)) break;
        }

        // Loan Amount
        double loanAmount = -1;
        while (loanAmount < 0) {
            System.out.print("Loan Amount (RWF): ");
            loanAmount = InputValidator.parsePositiveDouble(scanner.nextLine(), "Loan Amount");
            if (loanAmount > 0 && !InputValidator.isValidLoanAmount(loanAmount)) loanAmount = -1;
        }

        // Interest Rate
        double interestRate = -1;
        while (interestRate < 0) {
            System.out.print("Annual Interest Rate (%): ");
            interestRate = InputValidator.parsePositiveDouble(scanner.nextLine(), "Interest Rate");
            if (interestRate > 0 && !InputValidator.isValidInterestRate(interestRate)) interestRate = -1;
        }

        // Duration
        int duration = -1;
        while (duration < 0) {
            System.out.print("Loan Duration (months): ");
            duration = InputValidator.parsePositiveInt(scanner.nextLine(), "Loan Duration");
            if (duration > 0 && !InputValidator.isValidDuration(duration)) duration = -1;
        }

        // Officer & Branch
        String officer = "";
        while (true) {
            System.out.print("Loan Officer Name: ");
            officer = scanner.nextLine().trim();
            if (InputValidator.isNonEmpty(officer, "Officer Name")) break;
        }

        String branch = "";
        while (true) {
            System.out.print("Branch Location: ");
            branch = scanner.nextLine().trim();
            if (InputValidator.isNonEmpty(branch, "Branch Location")) break;
        }

        // Monthly income for eligibility check
        double income = -1;
        while (income < 0) {
            System.out.print("Customer Monthly Income (RWF): ");
            income = InputValidator.parsePositiveDouble(scanner.nextLine(), "Monthly Income");
        }

        // Create loan via factory (polymorphism)
        LoanManager loan = LoanFactory.createLoan(loanType, loanId, loanAmount,
                interestRate, duration, officer, branch, scanner);

        // Validate loan details
        if (!loan.validateLoanDetails()) {
            System.out.println(" Loan validation failed. Please check the details.");
            return;
        }

        // Eligibility check
        if (loan.checkEligibility(income)) {
            loan.approveLoan();
        } else {
            System.out.print(" Customer may not meet eligibility criteria. Override and approve? (yes/no): ");
            String override = scanner.nextLine().trim().toLowerCase();
            if (override.equals("yes") || override.equals("y")) {
                loan.approveLoan();
            } else {
                loan.rejectLoan("Failed eligibility/debt-to-income check");
            }
        }

        loans.add(loan);
        System.out.println("\n Loan application processed successfully!");
        System.out.println(loan);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 3. MAKE A PAYMENT
    // ══════════════════════════════════════════════════════════════════════════

    private static void makePayment() {
        if (loans.isEmpty()) {
            System.out.println(" No loans found. Please apply for a loan first.");
            return;
        }

        System.out.println("\n─── Make a Loan Payment ───");

        // Find loan
        System.out.print("Enter Loan ID to pay: ");
        String loanId = scanner.nextLine().trim();
        LoanManager loan = findLoanById(loanId);
        if (loan == null) {
            System.out.println(" Loan ID \"" + loanId + "\" not found.");
            return;
        }

        if (loan.getLoanStatus().equalsIgnoreCase("REJECTED")) {
            System.out.println(" Cannot make payment on a REJECTED loan.");
            return;
        }

        System.out.printf("  Remaining Balance: %,.2f RWF%n", loan.calculateRemainingBalance());

        // Payment amount
        double amount = -1;
        while (amount < 0) {
            System.out.print("Payment Amount (RWF): ");
            amount = InputValidator.parsePositiveDouble(scanner.nextLine(), "Payment Amount");
        }

        // Process via Payable interface method (polymorphism)
        loan.processPayment(amount);

        // Record repayment
        String paymentId = String.format("PAY-%04d", repaymentCounter++);
        Repayment repayment = new Repayment(paymentId, loan, amount, LocalDate.now());
        repayments.add(repayment);

        System.out.println(loan.generatePaymentReceipt());
        System.out.println(repayment);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 4. VIEW ALL LOANS
    // ══════════════════════════════════════════════════════════════════════════

    private static void viewAllLoans() {
        if (loans.isEmpty()) {
            System.out.println("  No loans to display.");
            return;
        }
        System.out.println("\n─── All Loans ───");
        for (LoanManager loan : loans) {
            System.out.println(loan);
            System.out.printf("  Monthly Install : %,.2f RWF%n", loan.calculateMonthlyInstallment());
            System.out.printf("  Total Repayment : %,.2f RWF%n", loan.calculateTotalRepayment());
            System.out.printf("  Remaining Balance: %,.2f RWF%n", loan.calculateRemainingBalance());
            System.out.println();
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 5. VIEW ALL CUSTOMERS
    // ══════════════════════════════════════════════════════════════════════════

    private static void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println(" No customers registered.");
            return;
        }
        System.out.println("\n─── Registered Customers ───");
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 6. VIEW REPAYMENT HISTORY
    // ══════════════════════════════════════════════════════════════════════════

    private static void viewRepaymentHistory() {
        if (repayments.isEmpty()) {
            System.out.println("  No repayments recorded yet.");
            return;
        }
        System.out.println("\n─── Repayment History ───");
        for (Repayment r : repayments) {
            System.out.println(r);
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 7. VIEW FULL LOAN REPORT
    // ══════════════════════════════════════════════════════════════════════════

    private static void viewLoanReport() {
        if (loans.isEmpty()) {
            System.out.println("  No loans to report.");
            return;
        }
        System.out.print("Enter Loan ID for full report (or ALL for all loans): ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("ALL")) {
            for (LoanManager loan : loans) {
                System.out.println(loan.generateLoanReport());
            }
        } else {
            LoanManager loan = findLoanById(input);
            if (loan == null) {
                System.out.println("  Loan ID \"" + input + "\" not found.");
            } else {
                System.out.println(loan.generateLoanReport());
            }
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // HELPER
    // ══════════════════════════════════════════════════════════════════════════

    private static LoanManager findLoanById(String loanId) {
        for (LoanManager loan : loans) {
            if (loan.getLoanId().equalsIgnoreCase(loanId)) return loan;
        }
        return null;
    }
}
