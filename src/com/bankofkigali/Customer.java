package com.bankofkigali;

public class Customer {

    private String customerId;
    private String customerName;
    private String nationalId;
    private String phoneNumber;

    // Default constructor
    public Customer() {
        this.customerId = "";
        this.customerName = "";
        this.nationalId = "";
        this.phoneNumber = "";
    }

    // Parameterized constructor
    public Customer(String customerId, String customerName,
                    String nationalId, String phoneNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getCustomerId()   { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getNationalId()   { return nationalId; }
    public String getPhoneNumber()  { return phoneNumber; }

    // Setters
    public void setCustomerId(String customerId)     { this.customerId = customerId; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setNationalId(String nationalId)     { this.nationalId = nationalId; }
    public void setPhoneNumber(String phoneNumber)   { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return String.format(
            "╔══════════════════════════════════════╗%n" +
            "  Customer ID   : %s%n" +
            "  Name          : %s%n" +
            "  National ID   : %s%n" +
            "  Phone         : %s%n" +
            "╚══════════════════════════════════════╝",
            customerId, customerName, nationalId, phoneNumber
        );
    }
}
