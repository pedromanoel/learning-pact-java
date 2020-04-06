package codes.pedromanoel.pact.customer

class CustomerService {
    fun findBy(customerId: CustomerId): Customer? =
            when (customerId) {
                CustomerId(1234) -> Customer(CustomerId(1234), "", "")
                else -> null
            }
}
