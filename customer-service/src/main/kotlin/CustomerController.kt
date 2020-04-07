package codes.pedromanoel.pact.customer

import io.javalin.http.Context

class CustomerController(private val ctx: Context, private val customerService: CustomerService) {

    fun get() {
        customerIdFrom()
                ?.let(customerService::findBy)
                ?.also(toJsonResponseUsing())
                ?: notFoundResponse()
    }

    private fun notFoundResponse() {
        ctx.status(404)
    }

    private fun toJsonResponseUsing(): (Customer) -> Unit =
            { customer -> ctx.json(customer) }

    private fun customerIdFrom(): CustomerId? {
        return ctx.pathParam("id")
                .toLongOrNull()
                ?.let(::CustomerId)
    }
}