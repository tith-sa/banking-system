package banking.system.util

fun Int.generatedAccountNum(): String {
    return this.toString().padStart(9, '0')
}