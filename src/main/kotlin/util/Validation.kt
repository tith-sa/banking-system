package banking.system.util

import java.math.BigDecimal

fun BigDecimal.validatedAmount() {
    if (this <= BigDecimal.ZERO) {
        throw IllegalArgumentException("Amount must be more than zero.")
    }
}