package banking.system.service

import banking.system.dto.Response
import banking.system.model.Account
import banking.system.model.Customer
import banking.system.model.enum.CurrencyEnum
import java.math.BigDecimal

interface AccountService {
    fun createAccount(owner: Customer, currency: CurrencyEnum, balance: BigDecimal = BigDecimal.ZERO.setScale(2)): Response<Account>
    fun displayUserAccounts(username: String): Response<List<Account>>
}