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
                ?.let { customerService.findBy(it) }
                ?.also { ctx.sendJsonResponse(it) }
                ?: ctx.customerNotFound()
    }

    override fun update(ctx: Context, resourceId: String) {
        TODO("Not yet implemented")
    }

}

private fun Context.sendJsonResponse(customer: Customer) = json(customer)
private fun Context.customerNotFound() = status(404)
private fun String.asCustomerId() = toLongOrNull()?.let(::CustomerId)