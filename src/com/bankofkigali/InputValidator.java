package com.bankofkigali;

import java.util.HashSet;
import java.util.Set;

public class InputValidator {

    // Track used IDs to catch duplicates
    private static final Set<String> usedLoanIds     = new HashSet<>();
    private static final Set<String> usedCustomerIds = new HashSet<>();
    private static final Set<String> usedNationalIds = new HashSet<>();

    // ── String / Empty ────────────────────────────────────────────────────────

    public static boolean isNonEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            System.out.println("ERROR: '" + fieldName + "' cannot be empty. Please enter a valid value.");
            return false;
        }
        return true;
    }

    // ── Numeric helpers ───────────────────────────────────────────────────────

    public static double parsePositiveDouble(String value, String fieldName) {
        if (!isNonEmpty(value, fieldName)) return -1;
        try {
            double d = Double.parseDouble(value.trim());
            if (d <= 0) {
                System.out.println(" ERROR: '" + fieldName + "' must be a positive number. Got: " + value);
                return -1;
            }
            return d;
        } catch (NumberFormatException e) {
            System.out.println("ERROR: '" + fieldName + "' must be a number, not text. Got: \"" + value + "\"");
            return -1;
        }
    }

    public static int parsePositiveInt(String value, String fieldName) {
        if (!isNonEmpty(value, fieldName)) return -1;
        try {
            int i = Integer.parseInt(value.trim());
            if (i <= 0) {
                System.out.println("ERROR: '" + fieldName + "' must be a positive integer. Got: " + value);
                return -1;
            }
            return i;
        } catch (NumberFormatException e) {
            System.out.println(" ERROR: '" + fieldName + "' must be a whole number, not \"" + value + "\"");
            return -1;
        }
    }

    // ── Loan amount ───────────────────────────────────────────────────────────

    public static boolean isValidLoanAmount(double amount) {
        if (amount <= 0) {
            System.out.println("ERROR: Loan amount must be greater than zero.");
            return false;
        }
        if (amount > 5_000_000_000.0) {
            System.out.println("ERROR: Loan amount exceeds the maximum allowed limit (5,000,000,000 RWF).");
            return false;
        }
        return true;
    }

    // ── Interest rate ─────────────────────────────────────────────────────────

    public static boolean isValidInterestRate(double rate) {
        if (rate < 0 || rate > 100) {
            System.out.println(" ERROR: Interest rate must be between 0% and 100%. Got: " + rate);
            return false;
        }
        return true;
    }

    // ── Phone number (Rwanda format: 07XXXXXXXX or +2507XXXXXXXX) ────────────

    public static boolean isValidPhoneNumber(String phone) {
        if (!isNonEmpty(phone, "Phone Number")) return false;
        String cleaned = phone.trim();
        // Accepts: 07XXXXXXXX  or  +2507XXXXXXXX
        if (!cleaned.matches("^(07\\d{8}|\\+2507\\d{8})$")) {
            System.out.println(" ERROR: Invalid phone number format: \"" + phone + "\"");
            System.out.println("   Expected formats: 07XXXXXXXX  or  +2507XXXXXXXX");
            return false;
        }
        return true;
    }

    // ── National ID (Rwanda: 16 digits) ──────────────────────────────────────

    public static boolean isValidNationalId(String nid) {
        if (!isNonEmpty(nid, "National ID")) return false;
        if (!nid.trim().matches("^\\d{16}$")) {
            System.out.println(" ERROR: Invalid National ID: \"" + nid + "\"");
            System.out.println("   National ID must be exactly 16 digits.");
            return false;
        }
        return true;
    }

    // ── Loan ID format (e.g. LN-2024-0001) ───────────────────────────────────

    public static boolean isValidLoanIdFormat(String loanId) {
        if (!isNonEmpty(loanId, "Loan ID")) return false;
        if (!loanId.trim().matches("^LN-\\d{4}-\\d{4}$")) {
            System.out.println(" ERROR: Invalid Loan ID format: \"" + loanId + "\"");
            System.out.println("   Expected format: LN-YYYY-NNNN  (e.g. LN-2024-0001)");
            return false;
        }
        return true;
    }

    // ── Customer ID format (e.g. CU-0001) ────────────────────────────────────

    public static boolean isValidCustomerIdFormat(String customerId) {
        if (!isNonEmpty(customerId, "Customer ID")) return false;
        if (!customerId.trim().matches("^CU-\\d{4}$")) {
            System.out.println(" ERROR: Invalid Customer ID format: \"" + customerId + "\"");
            System.out.println("   Expected format: CU-NNNN  (e.g. CU-0001)");
            return false;
        }
        return true;
    }

    // ── Loan type ─────────────────────────────────────────────────────────────

    public static boolean isValidLoanType(String type) {
        if (!isNonEmpty(type, "Loan Type")) return false;
        switch (type.trim().toUpperCase()) {
            case "PERSONAL":
            case "HOME":
            case "CAR":
            case "BUSINESS":
            case "STUDENT":
            case "AGRICULTURAL":
                return true;
            default:
                System.out.println(" ERROR: Invalid loan type: \"" + type + "\"");
                System.out.println("   Valid types: PERSONAL, HOME, CAR, BUSINESS, STUDENT, AGRICULTURAL");
                return false;
        }
    }

    // ── Duplicate ID checks ───────────────────────────────────────────────────

    public static boolean isUniqueLoanId(String loanId) {
        if (usedLoanIds.contains(loanId.trim().toUpperCase())) {
            System.out.println(" ERROR: Loan ID \"" + loanId + "\" already exists. Please use a unique ID.");
            return false;
        }
        usedLoanIds.add(loanId.trim().toUpperCase());
        return true;
    }

    public static boolean isUniqueCustomerId(String customerId) {
        if (usedCustomerIds.contains(customerId.trim().toUpperCase())) {
            System.out.println(" ERROR: Customer ID \"" + customerId + "\" already exists. Please use a unique ID.");
            return false;
        }
        usedCustomerIds.add(customerId.trim().toUpperCase());
        return true;
    }

    public static boolean isUniqueNationalId(String nationalId) {
        if (usedNationalIds.contains(nationalId.trim())) {
            System.out.println(" ERROR: National ID \"" + nationalId + "\" is already registered.");
            return false;
        }
        usedNationalIds.add(nationalId.trim());
        return true;
    }

    // ── Loan duration ─────────────────────────────────────────────────────────

    public static boolean isValidDuration(int months) {
        if (months < 1 || months > 360) {
            System.out.println(" ERROR: Loan duration must be between 1 and 360 months. Got: " + months);
            return false;
        }
        return true;
    }
}
