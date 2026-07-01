package banking.system.service

import banking.system.model.Transaction
import banking.system.model.enum.StatusEnum
import banking.system.model.enum.TransactionEnum
import banking.system.repository.AccountRepository
import java.math.BigDecimal
import java.util.UUID

class TransactionService(
    private val accountRepo : AccountRepository,
) {
    fun deposit(toAccount: String, amount: BigDecimal): Transaction {
        if (amount <= BigDecimal.ZERO) {
            throw IllegalArgumentException("Amount must be more than zero.")
        }
        val account = accountRepo.findNumberAccount(toAccount)
            ?: throw IllegalArgumentException("Account $toAccount not found.")
        val updatedAmount = account.copy(balance = account.balance + amount)
        accountRepo.savedAccount(updatedAmount)

        val depositedAmount = Transaction(
            UUID.randomUUID().toString(),
            TransactionEnum.DEPOSIT,
            null,
            account.accountNumber,
            account.currency,
            amount,
            StatusEnum.SUCCESS,
        )

        return depositedAmount
    }

    fun withdraw(toAccount: String, amount: BigDecimal): Transaction {
        if (amount <= BigDecimal.ZERO) {
            throw IllegalArgumentException("Amount must be more than zero.")
        }
        val account = accountRepo.findNumberAccount(toAccount)
            ?: throw IllegalArgumentException("Account $toAccount not found.")
        if (amount > account.balance) {
            throw IllegalArgumentException("Insufficient balance.")
        }
        val updatedAmount = account.copy(balance = account.balance - amount)
        accountRepo.savedAccount(updatedAmount)

        val withdrawalAmount = Transaction(
            UUID.randomUUID().toString(),
            TransactionEnum.WITHDRAW,
            account.accountNumber,
            null,
            account.currency,
            amount,
            StatusEnum.SUCCESS,
        )
        return withdrawalAmount
    }
}