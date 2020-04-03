package codes.pedromanoel.pact.client

import au.com.dius.pact.consumer.MockServer
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun MockServer.getExistingCustomerDetails() : HttpResponse<String> {
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(getUrl() + CUSTOMER_PATH))
        .header("Accept", "application/json")
        .build()

    return client.send(request, HttpResponse.BodyHandlers.ofString())
}