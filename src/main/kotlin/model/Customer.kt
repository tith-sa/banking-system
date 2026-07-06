package banking.system.model

data class Customer(
    val id: String,
    val username: String,
    val email: String
){
    override fun toString(): String {
        return """
            Username: $username
            Email: $email
        """
    }
}
