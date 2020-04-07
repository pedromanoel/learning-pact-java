package codes.pedromanoel.pact.client

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.annotations.Pact
import io.pactfoundation.consumer.dsl.LambdaDsl.newJsonBody
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

const val PROVIDER = "CustomerService"
const val CONSUMER = "Simple Client"

const val FIRST_NAME = "Test"
const val LAST_NAME = "First"

const val CUSTOMER_PATH = "/customers/1234"

@ExtendWith(PactConsumerTestExt::class)
class ConsumerServiceContractTest {

    @Pact(provider = PROVIDER, consumer = CONSUMER)
    fun `get details by id for existing customer pact`(builder: PactDslWithProvider): RequestResponsePact? {
        return builder
                .given("A customer with an existing ID")
                .uponReceiving("a request for customer details")
                .path(CUSTOMER_PATH)
                .headers(mapOf("Accept" to "application/json"))
                .willRespondWith()
                .headers(mapOf("Content-Type" to "application/json"))
                .body(
                        newJsonBody {
                            it.stringMatcher("firstName", "[A-Z][\\w\\s]+", FIRST_NAME)
                            it.stringMatcher("lastName", "[A-Z][\\w\\s]+", LAST_NAME)
                        }.build()
                )
                .status(200)
                .toPact()
    }

    @Test
    @PactTestFor(pactMethod = "get details by id for existing customer pact")
    internal fun `get details by id for existing customer`(server: MockServer) {
        val response = server.getExistingCustomerDetails()

        assertThat(response)
                .hasStatusOk()
                .containsHeader("Content-Type" to "application/json")
                .forCustomer(Customer(FIRST_NAME, LAST_NAME))
    }
}
