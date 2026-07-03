package banking.system

import banking.system.exception.toFailure
import banking.system.repository.AccountRepository
import banking.system.repository.CustomerRepository
import banking.system.repository.TransactionRepository
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
    val transactionRepo = TransactionRepository()

    val accountService : AccountService = AccountServiceImpl(accountRepo,customerRepo)
    val customerService : CustomerService = CustomerServiceImpl( customerRepo, accountService )
    val transactionService : TransactionService = TransactionServiceImpl(transactionRepo,accountRepo)

    val reader = Scanner(System.`in`)

    while (true) {
        println("=== Menu ===")
        println("1: Register")
        println("2: Display Account User")
        println("3: Deposit")
        println("4: Withdraw")
        println("5: Transfer")
        println("6: Transaction History")

        when (reader.nextLine()) {
            "1" -> {
                println("Register")
                print("Enter your username: ")
                val username = reader.nextLine()

                print("Enter your email: ")
                val email = reader.nextLine()

                try {
                    val user = customerService.register(username, email)
                    println(user)
                } catch (e: Exception) {
                    println(e.toFailure(" Error: ${e.message}"))
                }
            }
            "2" -> {
                println("Display Account User")
                print("Enter your username: ")
                val username = reader.nextLine()

                try {
                    val userAccounts = accountService.displayUserAccounts(username)

                    userAccounts.data?.forEach {
                        println("AccountNumber ${it.accountNumber} ${it.balance} ${it.currency}")
                    }
                } catch (e: Exception) {
                    println(e.toFailure("Error: ${e.message}"))
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
                    println(e.toFailure("Error: ${e.message}"))
                }
            }
            "4" -> {
                println("Withdraw")
                print("Enter Account Number: ")
                val accountNum = reader.nextLine()

                print("Enter Amount: ")
                val amount = reader.nextLine().toBigDecimal()

                try {
                    transactionService.withdraw(accountNum,amount)
                    println("Successfully Withdrawal!")
                }   catch (e: Exception) {
                    println(e.toFailure("Error: ${e.message}"))
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
                    println(e.toFailure("Error: ${e.message}"))
                }
            }
            "6" -> {
                println("Transaction History")
                print("Enter AccountNumber: ")
                val accountNum = reader.nextLine()

                try {
                   val history = transactionService.transactionHistory(accountNum)
                    history.data?.forEach {
                        println(it)
                    }
                } catch (e: Exception) {
                    println(e.toFailure("Error: ${e.message}"))
                }
            }
            else -> {
                break
            }
        }
    }
}