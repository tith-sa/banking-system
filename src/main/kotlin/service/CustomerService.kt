package banking.system.service

import banking.system.dto.Response
import banking.system.model.Customer

interface CustomerService {
    fun register(username: String, email: String): Response<Customer>
}