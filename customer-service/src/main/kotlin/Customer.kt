package codes.pedromanoel.pact.customer

import com.fasterxml.jackson.annotation.JsonValue

data class Customer(val id: CustomerId, val firstName: String, val lastName: String)

data class CustomerId(@JsonValue val value: Long)
