package banking.system.service

import banking.system.dto.Response
import banking.system.model.Transaction
import java.math.BigDecimal

interface TransactionService {
    fun deposit(toAccount: String, amount: BigDecimal): Response<Transaction>
    fun withdraw(fromAccount: String, amount: BigDecimal): Response<Transaction>
    fun transfer(fromAccount: String, toAccount: String, amount: BigDecimal): Response<Transaction>
    fun transactionHistory(accountNumber: String): Response<List<Transaction>>
}