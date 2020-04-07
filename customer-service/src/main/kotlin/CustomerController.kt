package codes.pedromanoel.pact.customer

import io.javalin.http.Context

class CustomerController(private val customerService: CustomerService) {

    fun getUser(ctx: Context) {
        getCustomerIdFrom(ctx)
                ?.let(customerService::findBy)
                ?.also(sendJsonResponse(ctx))
                ?: sendNotFoundResponse(ctx)
    }

    private fun sendNotFoundResponse(ctx: Context) {
        ctx.status(404)
    }

    private fun sendJsonResponse(ctx: Context): (Customer) -> Unit =
            { customer -> ctx.json(customer) }

    private fun getCustomerIdFrom(ctx: Context): CustomerId? {
        return ctx.pathParam("id")
                .toLongOrNull()
                ?.let(::CustomerId)
    }
}