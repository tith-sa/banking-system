package banking.system.util

import java.math.BigDecimal

fun BigDecimal.exchangeToKHR(exchangeRate: BigDecimal): BigDecimal {
    return this * exchangeRate
}

fun BigDecimal.exchangeToUSD(exchangeRate: BigDecimal): BigDecimal {
    return this / exchangeRate
}