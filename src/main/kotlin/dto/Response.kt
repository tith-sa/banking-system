package banking.system.dto

data class Response<T> (
    val success : Boolean,
    val status : String,
    val data: T?,
    val message : String,
){
    override fun toString(): String {
        return """
            Success: $success
            Status: $status
            Data: $data
            Message: $message
        """.trimIndent()
    }
}