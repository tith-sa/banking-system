package banking.system.util

import banking.system.model.enum.CurrencyEnum
import java.math.BigDecimal

fun BigDecimal.exchangeToKHR(exchangeRate: BigDecimal): BigDecimal {
    return this * exchangeRate
}

fun BigDecimal.exchangeToUSD(exchangeRate: BigDecimal): BigDecimal {
    return this / exchangeRate
}

fun BigDecimal.exchange(
    from: CurrencyEnum,
    to: CurrencyEnum,
    rate: BigDecimal
): BigDecimal {
    if (from == to) return this
    return when (to) {
        CurrencyEnum.USD -> exchangeToUSD(rate)
        CurrencyEnum.KHR -> exchangeToKHR(rate)
    }
}