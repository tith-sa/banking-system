package banking.system.util

import banking.system.exception.BadRequestException
import banking.system.model.enum.CurrencyEnum
import java.math.BigDecimal

fun BigDecimal.validatedAmount(currency : CurrencyEnum, exchangeRate : BigDecimal) {
    val maxTransaction = BigDecimal("10000") // USD

    if (this <= BigDecimal.ZERO) {
        throw BadRequestException("Amount must be more than zero.")
    }

    val amountInUsd = this.exchange(
         currency,
         CurrencyEnum.USD,
         exchangeRate
    )

    if (amountInUsd > maxTransaction) {
        throw BadRequestException("Maximum transaction amount is $10,000 USD.")
    }
}