package banking.system.model


import banking.system.model.enum.CurrencyEnum
import banking.system.model.enum.StatusEnum
import banking.system.model.enum.TransactionEnum
import java.math.BigDecimal

data class Transaction(
    val id : String,
    val type : TransactionEnum,
    val fromAccount : String?,
    val toAccount : String?,
    val currency: CurrencyEnum,
    val amount: BigDecimal,
    val status: StatusEnum
)
