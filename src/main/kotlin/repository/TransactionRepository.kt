package banking.system.repository

import banking.system.model.Transaction

class TransactionRepository {
    private val transactions = mutableListOf<Transaction>()

    fun save(transaction: Transaction) { transactions.add(transaction) }
    fun findByAccount(accountNumber: String): List<Transaction> = transactions.filter {
        it.toAccount == accountNumber || it.fromAccount == accountNumber
    }
}