package banking.system.service.impl

import banking.system.model.Customer
import banking.system.model.enum.CurrencyEnum
import banking.system.repository.CustomerRepository
import banking.system.service.AccountService
import banking.system.service.CustomerService
import java.util.UUID

class CustomerServiceImpl (
    private val customerRepo: CustomerRepository,
    private val accountService: AccountService
) : CustomerService {
    override fun register(username: String, email: String) : Customer {
        if (customerRepo.existsByUsername(username)) {
            throw IllegalArgumentException("Customer already exists")
        }
        if (customerRepo.existsByEmail(email)) {
            throw IllegalArgumentException("Email already exists")
        }
        val customer = Customer(
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