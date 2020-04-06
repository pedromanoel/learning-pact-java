package codes.pedromanoel.pact.customer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CustomerServiceTest {

    private val service = CustomerService()

    @Test
    internal fun `return a customer`() {
        val customer : Customer? = service.findBy(CustomerId(1234))

        assertThat(customer).isNotNull
        assertThat(customer?.id)
                .isEqualTo(CustomerId(1234))
    }
}