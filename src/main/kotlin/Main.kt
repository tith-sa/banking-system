package banking.system

import banking.system.repository.AccountRepository
import banking.system.repository.CustomerRepository
import banking.system.service.AccountService
import banking.system.service.CustomerService
import banking.system.service.TransactionService
import banking.system.service.impl.AccountServiceImpl
import banking.system.service.impl.CustomerServiceImpl
import banking.system.service.impl.TransactionServiceImpl
import java.util.Scanner

fun main() {
    val customerRepo = CustomerRepository()
    val accountRepo = AccountRepository()

    val accountService : AccountService = AccountServiceImpl(accountRepo,customerRepo)
    val customerService : CustomerService = CustomerServiceImpl( customerRepo, accountService )
    val transactionService : TransactionService = TransactionServiceImpl(accountRepo)

    val reader = Scanner(System.`in`)

    while (true) {
        println("=== Menu ===")
        println("1: Register")
        println("2: Display Account User")
        println("3: Deposit")
        println("4: Withdraw")
        println("5: Transfer")

        when (reader.nextLine()) {
            "1" -> {
                println("Register")
                print("Enter your username: ")
                val username = reader.nextLine()

                print("Enter your email: ")
                val email = reader.nextLine()

                try {
                    customerService.register(username, email)
                    println("Successfully Registered!")
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
            "2" -> {
                println("Display Account User")
                print("Enter your username: ")
                val username = reader.nextLine()

                try {
                    accountService.displayUserAccounts(username).forEach {
                        println("Account Number: ${it.accountNumber} balance: ${it.balance} ${it.currency}")
                    }
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }

            }
            "3" -> {
                println("Deposit")
                print("Enter Account Number: ")
                val accountNum = reader.nextLine()

                print("Enter Amount: ")
                val amount = reader.nextLine().toBigDecimal()

                try {
                    transactionService.deposit(accountNum,amount)
                    println("Successfully Deposited!")
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
            "4" ->{
                println("Withdraw")
                print("Enter Account Number: ")
                val accountNum = reader.nextLine()

                print("Enter Amount: ")
                val amount = reader.nextLine().toBigDecimal()

                try {
                    transactionService.withdraw(accountNum,amount)
                    println("Successfully Withdrawal!")
                }   catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
            "5" -> {
                println("Transfer")
                print("Enter Sender Account Number: ")
                val senderAccountNum = reader.nextLine()

                print("Enter Receiver Account Number: ")
                val receiverAccountNum = reader.nextLine()

                print("Enter Amount: ")
                val amount = reader.nextLine().toBigDecimal()

                try {
                    transactionService.transfer(senderAccountNum,receiverAccountNum,amount)
                    println("Successfully Transfer!")
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
            else -> {
                break
            }
        }
    }
}