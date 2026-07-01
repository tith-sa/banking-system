package banking.system.model

import banking.system.model.enum.CurrencyEnum
import java.math.BigDecimal


data class Account (
    val id: String,
    val ownerId: String,
    val accountNumber: String,
    val currency: CurrencyEnum,
    val balance: BigDecimal = BigDecimal.ZERO.setScale(2),
)