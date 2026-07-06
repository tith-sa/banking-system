package banking.system.util

import banking.system.exception.BadRequestException
import java.math.BigDecimal

fun BigDecimal.validatedAmount() {
    if (this <= BigDecimal.ZERO) {
        throw BadRequestException("Amount must be more than zero.")
    }
}