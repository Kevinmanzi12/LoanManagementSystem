package com.bankofkigali;

public class CarLoan extends LoanManager {

    private String vehicleModel; // specific attribute

    public CarLoan() {
        super();
        setLoanType("Car Loan");
        this.vehicleModel = "";
    }

    public CarLoan(String loanId, double loanAmount, double interestRate,
                   int loanDuration, String officerName, String branchLocation,
                   String vehicleModel) {
        super(loanId, "Car Loan", loanAmount, interestRate, loanDuration, officerName, branchLocation);
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel()            { return vehicleModel; }
    public void setVehicleModel(String model)  { this.vehicleModel = model; }

    @Override
    public boolean checkEligibility(double monthlyIncome) {
        // Car loans: max duration 60 months
        if (getLoanDuration() > 60) return false;
        return super.checkEligibility(monthlyIncome);
    }

    @Override
    public double calculateMonthlyInstallment() {
        // Add a small insurance surcharge (0.5 % of loan per year ÷ 12)
        double base = super.calculateMonthlyInstallment();
        double insuranceSurcharge = (getLoanAmount() * 0.005) / 12;
        return base + insuranceSurcharge;
    }

    @Override
    public String generateLoanReport() {
        return super.generateLoanReport() +
            "Vehicle Model      : " + vehicleModel + "\n";
    }

    @Override
    public String toString() {
        return super.toString() +
            String.format("%n  Vehicle Model : %s", vehicleModel);
    }
}
