package banking.system.service.impl

import banking.system.dto.Response
import banking.system.exception.BadRequestException
import banking.system.exception.StatusException
import banking.system.util.toSuccess
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
    override fun register(username: String, email: String) : Response<Customer> {
        if (customerRepo.existsByUsername(username)) {
            throw BadRequestException("Customer already exists")
        }
        if (customerRepo.existsByEmail(email)) {
            throw BadRequestException("Email already exists")
        }
        val customer = Customer(
            UUID.randomUUID().toString(),
            username,
            email
        )
        customerRepo.savedUser(customer)
        accountService.createAccount(customer, CurrencyEnum.KHR)
        accountService.createAccount(customer, CurrencyEnum.USD)

        return customer.toSuccess(StatusException.CREATED,"User registered successfully")
    }

}