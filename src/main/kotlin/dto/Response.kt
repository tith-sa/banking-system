package banking.system.dto

data class Response<T> (
    val success : Boolean,
    val status : String,
    val data: T?,
    val message : String,
)