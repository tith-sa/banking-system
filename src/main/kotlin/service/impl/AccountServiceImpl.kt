package banking.system.service.impl

import banking.system.dto.Response
import banking.system.exception.BadRequestException
import banking.system.exception.NotFoundException
import banking.system.exception.StatusException
import banking.system.util.toSuccess
import banking.system.model.Account
import banking.system.model.Customer
import banking.system.model.enum.CurrencyEnum
import banking.system.repository.AccountRepository
import banking.system.repository.CustomerRepository
import banking.system.service.AccountService
import banking.system.util.generatedAccountNum
import java.math.BigDecimal
import java.util.UUID

class AccountServiceImpl(
    private val accountRepo: AccountRepository,
    private val customerRepo: CustomerRepository,
) : AccountService {

    override fun createAccount(
        owner: Customer,
        currency: CurrencyEnum,
        balance: BigDecimal
    ): Response<Account> {

        val accountNumber = accountRepo.findAllAccounts().size.generatedAccountNum()
        if (accountRepo.existsAccountNumber(accountNumber)) {
            throw BadRequestException("An account already exists.")
        }
        val account = Account(
            UUID.randomUUID().toString(),
            owner.id,
            accountNumber,
            currency,
            balance
        )
        accountRepo.savedAccount(account)
        return account.toSuccess(StatusException.CREATED,"Created Account $currency successfully.")
    }

    override fun displayUserAccounts(username: String): Response<List<Account>> {
        val customer = customerRepo.findByUsername(username)
            ?: throw NotFoundException("Username $username not found.")
        val accounts = accountRepo.findUserAccount(customer.id)
    return accounts.toSuccess(message = "Customer ${customer.username} has ${accounts.size} accounts.")
    }
}