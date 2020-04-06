package codes.pedromanoel.pact.client

import au.com.dius.pact.consumer.MockServer
import kong.unirest.HttpResponse
import kong.unirest.Unirest

fun MockServer.getExistingCustomerDetails() : HttpResponse<String> {
    return Unirest
            .get(getUrl() + CUSTOMER_PATH)
            .header("Accept", "application/json")
            .asString()
}