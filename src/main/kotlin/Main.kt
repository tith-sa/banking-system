package banking.system

import banking.system.repository.AccountRepository
import banking.system.repository.CustomerRepository
import banking.system.service.AccountService
import banking.system.service.CustomerService
import java.util.Scanner

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val customerRepo = CustomerRepository()
    val accountRepo = AccountRepository()

    val customerService = CustomerService( customerRepo, AccountService(accountRepo, customerRepo))
    val accountService = AccountService(accountRepo,customerRepo)

    val reader = Scanner(System.`in`)

    while (true) {
        println("=== Menu ===")
        println("1: Register")
        println("2: Display Account User")

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
            else -> {
                break
            }
        }
    }
}