package banking.system.service

import banking.system.model.Account
import banking.system.model.Customer
import banking.system.model.enum.CurrencyEnum
import java.math.BigDecimal

interface AccountService {
    fun createAccount(owner: Customer, currency: CurrencyEnum, balance: BigDecimal = BigDecimal.ZERO.setScale(2)): Account
    fun displayUserAccounts(username: String): List<Account>
}