# banking-system

A simple banking system built with Kotlin to understand Kotlin foundation, practice object-oriented programming, repository pattern, service layer, exception handling, and business logic.

## Features

1. Register Customer
2. Create Account
3. Deposit
4. Withdraw
5. Transfer
6. Get Balance
7. Get Transaction History


## Technologies

- Kotlin
- Gradle
- BigDecimal
- UUID

## Project Structure

```
src
└── main
    └── kotlin
        └── banking
            ├── model
            ├── repository
            ├── service
            ├── exception
            ├── util
            └── Main.kt
```

## Business Rules

### Registration

- A customer can register with a unique username and email.
- Every customer receives:
    - One KHR account
    - One USD account

### Deposit

- Deposit must be greater than zero.
- Deposit is made into a single account.

### Withdraw

- Withdrawal amount must be greater than zero.
- Account must have sufficient balance.

### Transfer

- Sender and receiver cannot be the same account.
- Sender must have sufficient balance.
- Currency conversion is performed automatically when currencies differ.
- Every transfer creates a transaction record.

## Example

```
=== Menu ===
1. Register
2. Display Accounts
3. Deposit
4. Withdraw
5. Transfer
6. Transaction History
```

## Running the Project

Clone the repository:

```bash
git clone <repository-url>
```

Run:`Main.kt` from IntelliJ IDEA.

## Future Improvements

- PostgreSQL database
- JDBC
- Login system
- Transaction rollback
- Interest calculation
- Account statement
- REST API (Spring Boot)
- Unit tests

## Author

Tith Sa
