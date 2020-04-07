package codes.pedromanoel.pact.customer

import kong.unirest.Unirest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class CustomersControllerTest {
    private val app = App(customerService = CustomerService())

    @BeforeAll
    internal fun `start app`() {
        app.start()
    }

    @AfterAll
    internal fun `stop app`() {
        app.stop()
    }

    @Test
    internal fun `correctly map customer to json`() {
        val customerJson = Unirest
                .get("http://localhost:7000$CUSTOMERS_PATH/1234")
                .asString()

        assertThat(customerJson.body).isEqualTo("""
            {"id":1234,"firstName":"Name","lastName":"Surname"}
        """.trimIndent())
    }
}