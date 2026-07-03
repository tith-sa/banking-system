package banking.system.util

import banking.system.model.enum.CurrencyEnum
import java.math.BigDecimal

fun BigDecimal.exchange(
    from: CurrencyEnum,
    to: CurrencyEnum,
    exchangeRate: BigDecimal
): BigDecimal {
    if (from == to) return this
    return when (to) {
        CurrencyEnum.USD -> { this / exchangeRate }
        CurrencyEnum.KHR -> { this * exchangeRate }
    }
}