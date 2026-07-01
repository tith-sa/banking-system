package banking.system.service

import banking.system.model.Account
import banking.system.model.Customer
import banking.system.model.enum.CurrencyEnum
import banking.system.repository.AccountRepository
import banking.system.repository.CustomerRepository
import banking.system.util.generatedAccountNum
import java.math.BigDecimal
import java.util.UUID

class AccountService(
    private val accountRepo: AccountRepository,
    private val customerRepo: CustomerRepository,
) {

    fun createAccount(
        owner: Customer,
        currencyEnum: CurrencyEnum,
        balance: BigDecimal = BigDecimal.ZERO.setScale(2)
    ): Account {

        val accountNumber = accountRepo.findAllAccounts().size.generatedAccountNum()
        if (accountRepo.existsAccountNumber(accountNumber)) {
            throw IllegalArgumentException("An account already exists.")
        }
        val account = Account(
             UUID.randomUUID().toString(),
             owner.id,
             accountNumber,
             currencyEnum,
             balance
        )
        accountRepo.savedAccount(account)
        return account
    }

    fun displayUserAccounts(username: String): List<Account> {
        val customer = customerRepo.findByUsername(username)
            ?: throw IllegalArgumentException("Username $username not found.")
        val accounts = accountRepo.findUserAccount(customer.id)
        return accounts
    }
}