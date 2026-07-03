package banking.system.service.impl

import banking.system.exception.Response
import banking.system.exception.toSuccess
import banking.system.model.Transaction
import banking.system.model.enum.StatusEnum
import banking.system.model.enum.TransactionEnum
import banking.system.repository.AccountRepository
import banking.system.repository.TransactionRepository
import banking.system.service.TransactionService
import banking.system.util.exchange
import banking.system.util.validatedAmount
import java.math.BigDecimal
import java.util.UUID

class TransactionServiceImpl(
    private val transactionRepo: TransactionRepository,
    private val accountRepo : AccountRepository
) : TransactionService {
    private val exchangeRate = 4000.toBigDecimal()

    override fun deposit(toAccount: String, amount: BigDecimal): Response<Transaction> {
        amount.validatedAmount()
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
        transactionRepo.save(depositedAmount)
        return depositedAmount.toSuccess("Deposited successfully")
    }

    override fun withdraw(fromAccount: String, amount: BigDecimal): Response<Transaction> {
        amount.validatedAmount()
        val account = accountRepo.findNumberAccount(fromAccount)
            ?: throw IllegalArgumentException("Account $fromAccount not found.")
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
        transactionRepo.save(withdrawalAmount)
        return withdrawalAmount.toSuccess("Withdrawal successfully")
    }

    override fun transfer(fromAccount: String ,toAccount: String, amount: BigDecimal): Response<Transaction> {
        amount.validatedAmount()

        val fromAccount = accountRepo.findNumberAccount(fromAccount)
            ?: throw IllegalArgumentException("Account $fromAccount not found.")
        val toAccount = accountRepo.findNumberAccount(toAccount)
            ?: throw IllegalArgumentException("Account $toAccount not found.")

        if (fromAccount == toAccount) {
            throw IllegalArgumentException("Cannot transfer to this account.")
        }
        if (amount > fromAccount.balance) {
            throw IllegalArgumentException("Insufficient balance.")
        }
        val convertedExchangeAmount = amount.exchange(
            fromAccount.currency,
            toAccount.currency,
            exchangeRate,
        )

        val updatedAmountSender = fromAccount.copy(balance = fromAccount.balance - amount)
        val updatedAmountReceiver = toAccount.copy(balance = toAccount.balance + convertedExchangeAmount)

        accountRepo.savedAccount(updatedAmountSender)
        accountRepo.savedAccount(updatedAmountReceiver)

        val transaction = Transaction(
            UUID.randomUUID().toString(),
            TransactionEnum.TRANSFER,
            fromAccount.accountNumber,
            toAccount.accountNumber,
            fromAccount.currency,
            amount,
            StatusEnum.SUCCESS,
        )
        transactionRepo.save(transaction)
        return transaction.toSuccess("Transferred successfully")
    }

    override fun transactionHistory(accountNumber: String): Response<List<Transaction>> {
        if (!accountRepo.existsAccountNumber(accountNumber)) {
            throw IllegalArgumentException("Account $accountNumber not found.")
        }
        val transaction = transactionRepo.findByAccount(accountNumber)
        return transaction.toSuccess("Get transaction successfully")
    }
}