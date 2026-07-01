package banking.system.repository

import banking.system.model.Customer

class CustomerRepository {
    private val customers = mutableMapOf<String,Customer>()

    fun savedUser(customer: Customer) { customers[customer.id] = customer }
    fun existsByUsername(username : String) = customers.values.any { it.username == username }
    fun existsByEmail(email : String) = customers.values.any { it.email == email }
    fun findByUsername(username : String) = customers.values.find { it.username == username }

}