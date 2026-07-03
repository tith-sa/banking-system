package banking.system.exception

data class Response<T> (
    val success : Boolean,
    val data: T?,
    val message : String,
)