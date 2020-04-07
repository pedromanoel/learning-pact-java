package codes.pedromanoel.pact.customer

import io.javalin.apibuilder.CrudHandler
import io.javalin.http.Context

class CustomerController(private val customerService: CustomerService) : CrudHandler {

    override fun create(ctx: Context) {
        TODO("Not yet implemented")
    }

    override fun delete(ctx: Context, resourceId: String) {
        TODO("Not yet implemented")
    }

    override fun getAll(ctx: Context) {
        TODO("Not yet implemented")
    }

    override fun getOne(ctx: Context, resourceId: String) {
        resourceId.asCustomerId()
                ?.let(customerService::findBy)
                ?.also(sendJsonResponse(ctx))
                ?: sendNotFoundResponse(ctx)
    }

    private fun sendNotFoundResponse(ctx: Context) {
        ctx.status(404)
    }

    private fun sendJsonResponse(ctx: Context): (Customer) -> Unit =
            { customer -> ctx.json(customer) }

    override fun update(ctx: Context, resourceId: String) {
        TODO("Not yet implemented")
    }
}

private fun String.asCustomerId(): CustomerId? =
        toLongOrNull()?.let(::CustomerId)