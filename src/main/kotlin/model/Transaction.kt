package banking.system.model


import banking.system.model.enum.CurrencyEnum
import banking.system.model.enum.StatusEnum
import banking.system.model.enum.TransactionEnum
import java.math.BigDecimal

data class Transaction(
    val id : String,
    val senderAccount : String,
    val receiverAccount : String,
    val currency: CurrencyEnum,
    val amount: BigDecimal,
    val type : TransactionEnum,
    val status: StatusEnum
)
