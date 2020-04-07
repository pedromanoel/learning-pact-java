package codes.pedromanoel.pact.customer

import io.javalin.http.Context

class CustomerController(private val customerService: CustomerService) {

    fun getUser(ctx: Context) {
        customerIdFrom(ctx)
                ?.let(customerService::findBy)
                ?.also(toJsonResponseUsing(ctx))
                ?: notFoundResponse(ctx)
    }

    private fun notFoundResponse(ctx: Context) {
        ctx.status(404)
    }

    private fun toJsonResponseUsing(ctx: Context): (Customer) -> Unit =
            { customer -> ctx.json(customer) }

    private fun customerIdFrom(ctx: Context): CustomerId? {
        return ctx.pathParam("id")
                .toLongOrNull()
                ?.let(::CustomerId)
    }
}