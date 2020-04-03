package codes.pedromanoel.pact.client

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import java.net.http.HttpResponse

class HttpResponseAsserts(private val response: HttpResponse<String>) {
    fun hasStatusOk() {
        Assertions.assertEquals(
            200,
            response.statusCode(),
            "should be 200 OK"
        )
    }

    fun containsHeader(headerPair: Pair<String, String>) {
        val (key, value) = headerPair
        val headers = response.headers()
        Assertions.assertTrue(
            headers.allValues(key).contains(value),
            "$headers should contain $headerPair"
        )
    }

    fun forCustomer(expectedCustomer: Customer) {
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
    Gson().fromJson(body(), Customer::class.java)