package banking.system.service


import banking.system.model.Customer
import banking.system.model.enum.CurrencyEnum
import banking.system.repository.CustomerRepository
import java.util.UUID

class CustomerService (
    private val customerRepo: CustomerRepository,
    private val accountService: AccountService
) {
    fun register(username: String, email: String) : Customer {
        if (customerRepo.existsByUsername(username)) {
            throw IllegalArgumentException("Customer already exists")
        }
        if (customerRepo.existsByEmail(email)) {
            throw IllegalArgumentException("Email already exists")
        }
        val customer = Customer (
            UUID.randomUUID().toString(),
            username,
            email
        )
        customerRepo.savedUser(customer)
        accountService.createAccount(customer, CurrencyEnum.KHR)
        accountService.createAccount(customer, CurrencyEnum.USD)
        return customer
    }

}