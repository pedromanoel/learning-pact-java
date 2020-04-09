import codes.pedromanoel.pact.client.Customer
import com.google.gson.Gson
import kong.unirest.HttpResponse
import org.junit.jupiter.api.Assertions

class HttpResponseAsserts(private val response: HttpResponse<String>) {
    fun hasStatusOk() = apply {
        Assertions.assertEquals(
                200,
                response.status,
                "should be 200 OK"
        )
    }

    fun containsHeader(headerPair: Pair<String, String>) = apply {
        val (key, value) = headerPair
        Assertions.assertTrue(
                response.headers.get(key).contains(value),
                "${response.headers} should contain $headerPair"
        )
    }

    fun forCustomer(expectedCustomer: Customer) = apply {
        val actualCustomer = response.bodyAsCustomer()

        Assertions.assertEquals(
                expectedCustomer,
                actualCustomer,
                "$actualCustomer should be equal to $expectedCustomer"
        )
    }
}

fun assertThat(response: HttpResponse<String>) = HttpResponseAsserts(response)

private fun HttpResponse<String>.bodyAsCustomer() =
        Gson().fromJson(body, codes.pedromanoel.pact.client.Customer::class.java)