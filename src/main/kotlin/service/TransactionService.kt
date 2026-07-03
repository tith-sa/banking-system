package banking.system.service

import banking.system.model.Transaction
import java.math.BigDecimal

interface TransactionService {
    fun deposit(toAccount: String, amount: BigDecimal): Transaction
    fun withdraw(fromAccount: String, amount: BigDecimal): Transaction
    fun transfer(fromAccount: String, toAccount: String, amount: BigDecimal): Transaction
}