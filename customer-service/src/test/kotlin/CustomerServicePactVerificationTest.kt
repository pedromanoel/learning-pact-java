package codes.pedromanoel.pact.customer

import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.loader.PactBroker
import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith

@Provider("CustomerService")
@PactBroker(
        host = "localhost",
        port = "9292")
class CustomerServicePactVerificationTest {

    private val app: App = App()

    @BeforeAll
    internal fun appSetup() {
        app.start()
    }

    @AfterAll
    internal fun appTearDown() {
        app.stop()
    }

    @BeforeEach
    internal fun setUp(context: PactVerificationContext) {
        context.target = HttpTestTarget(port = 7000)
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }
}