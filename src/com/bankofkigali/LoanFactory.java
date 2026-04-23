package com.bankofkigali;

import java.util.Scanner;

/**
 * LoanFactory – creates the correct LoanManager subclass based on loan type.
 * Demonstrates POLYMORPHISM: the returned reference is always LoanManager,
 * but the actual runtime object is a specific subclass.
 */
public class LoanFactory {

    /**
     * Prompt user for the subclass-specific attribute, then build and return
     * the appropriate LoanManager subclass.
     *
     * @param type           one of: PERSONAL, HOME, CAR, BUSINESS, STUDENT, AGRICULTURAL
     * @param loanId         validated loan ID
     * @param loanAmount     validated amount
     * @param interestRate   validated rate
     * @param loanDuration   validated duration in months
     * @param officerName    name of the approving officer
     * @param branchLocation branch handling the loan
     * @param scanner        shared Scanner for user input
     * @return               a LoanManager subclass reference (polymorphism)
     */
    public static LoanManager createLoan(String type,
                                         String loanId,
                                         double loanAmount,
                                         double interestRate,
                                         int loanDuration,
                                         String officerName,
                                         String branchLocation,
                                         Scanner scanner) {

        switch (type.trim().toUpperCase()) {

            case "PERSONAL": {
                System.out.print("  Enter purpose of loan: ");
                String purpose = scanner.nextLine().trim();
                if (purpose.isEmpty()) purpose = "General Purpose";
                return new PersonalLoan(loanId, loanAmount, interestRate,
                        loanDuration, officerName, branchLocation, purpose);
            }

            case "HOME": {
                double propValue = -1;
                while (propValue < 0) {
                    System.out.print("  Enter property value (RWF): ");
                    propValue = InputValidator.parsePositiveDouble(scanner.nextLine(), "Property Value");
                }
                return new HomeLoan(loanId, loanAmount, interestRate,
                        loanDuration, officerName, branchLocation, propValue);
            }

            case "CAR": {
                System.out.print("  Enter vehicle model (e.g. Toyota RAV4 2022): ");
                String model = scanner.nextLine().trim();
                if (model.isEmpty()) model = "Unknown Model";
                return new CarLoan(loanId, loanAmount, interestRate,
                        loanDuration, officerName, branchLocation, model);
            }

            case "BUSINESS": {
                System.out.print("  Enter collateral type (e.g. Land Title, Building): ");
                String collateral = scanner.nextLine().trim();
                if (collateral.isEmpty()) collateral = "None";
                return new BusinessLoan(loanId, loanAmount, interestRate,
                        loanDuration, officerName, branchLocation, collateral);
            }

            case "STUDENT": {
                System.out.print("  Enter university name: ");
                String university = scanner.nextLine().trim();
                if (university.isEmpty()) university = "Unknown University";
                return new StudentLoan(loanId, loanAmount, interestRate,
                        loanDuration, officerName, branchLocation, university);
            }

            case "AGRICULTURAL": {
                double farmSize = -1;
                while (farmSize < 0) {
                    System.out.print("  Enter farm size (hectares): ");
                    farmSize = InputValidator.parsePositiveDouble(scanner.nextLine(), "Farm Size");
                }
                return new AgriculturalLoan(loanId, loanAmount, interestRate,
                        loanDuration, officerName, branchLocation, farmSize);
            }

            default:
                System.out.println("  Unknown loan type: " + type + ". Defaulting to PersonalLoan.");
                return new PersonalLoan(loanId, loanAmount, interestRate,
                        loanDuration, officerName, branchLocation, "General Purpose");
        }
    }
}
