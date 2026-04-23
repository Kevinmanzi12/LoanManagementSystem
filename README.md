# Bank of Kigali – Loan Management System

A complete Object-Oriented Java application for managing loan products,
customers, and repayment operations.

---

## OOP Concepts Demonstrated

| Concept        | Where Applied |
|----------------|---------------|
| **Encapsulation** | Private fields + getters/setters in every class |
| **Abstraction**   | `Loan` abstract class with 8 abstract methods |
| **Interface**     | `Payable` interface (processPayment, calculateRemainingBalance, generatePaymentReceipt) |
| **Inheritance**   | `LoanManager` extends `Loan`; 6 loan subclasses extend `LoanManager` |
| **Polymorphism**  | `LoanFactory` returns `LoanManager` references at runtime; method overriding |
| **Constructors**  | Default & parameterised in every class |
| **Method Override** | `toString()`, `calculateInterest()`, `checkEligibility()`, etc. |

---

## Project Structure

```
src/com/bankofkigali/
├── Loan.java                 ← Abstract base class (8 abstract methods)
├── Payable.java              ← Interface
├── LoanManager.java          ← Extends Loan, implements Payable
├── PersonalLoan.java         ← Extends LoanManager
├── HomeLoan.java
├── CarLoan.java
├── BusinessLoan.java
├── StudentLoan.java
├── AgriculturalLoan.java
├── Customer.java
├── Repayment.java
├── InputValidator.java       ← Full validation with clear error messages
├── LoanFactory.java          ← Factory pattern / polymorphism
└── LoanSystem.java           ← Main class with interactive menu
```

---

## How to Run

### Option 1 – Run the JAR directly
```bash
java -jar BankOfKigali-LMS.jar
```

### Option 2 – Build & Run with Docker
```bash
docker build -t bank-of-kigali-lms .
docker run -it bank-of-kigali-lms
```

### Option 3 – Compile from source
```bash
mkdir -p out
find src -name "*.java" | xargs javac -d out
echo "Main-Class: com.bankofkigali.LoanSystem" > manifest.txt && echo "" >> manifest.txt
jar cfm BankOfKigali-LMS.jar manifest.txt -C out .
java -jar BankOfKigali-LMS.jar
```

---

## Loan Types

| Type          | Specific Attribute  | Interest Modifier |
|---------------|---------------------|-------------------|
| Personal      | Purpose of Loan     | ×1.5 premium      |
| Home          | Property Value      | ×0.9 discount     |
| Car           | Vehicle Model       | +insurance surcharge |
| Business      | Collateral Type     | ×1.2 risk premium |
| Student       | University Name     | ×0.5 subsidised   |
| Agricultural  | Farm Size (hectares)| ×0.7 gov-subsidised |

---

## Validation Rules

- Loan ID format: `LN-YYYY-NNNN` (e.g. `LN-2024-0001`)
- Customer ID format: `CU-NNNN` (e.g. `CU-0001`)
- National ID: exactly 16 digits
- Phone: `07XXXXXXXX` or `+2507XXXXXXXX`
- Interest rate: 0–100%
- Loan amount: > 0, max 5,000,000,000 RWF
- Duration: 1–360 months
- Duplicate ID detection for Loan ID, Customer ID, and National ID
"# LoanManagementSystem" 
