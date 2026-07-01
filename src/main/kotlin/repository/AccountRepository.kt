package banking.system.repository

import banking.system.model.Account

class AccountRepository {
    private val accounts = mutableMapOf<String, Account>()

    fun savedAccount(account: Account) { accounts[account.id] = account }
    fun existsAccountNumber(accountNumber: String) = accounts.values.any { it.accountNumber == accountNumber }
    fun findAllAccounts(): List<Account>  = accounts.values.toList()
    fun findUserAccount(ownerId : String): List<Account> = accounts.values.filter { it.ownerId == ownerId }

}