package codes.pedromanoel.pact.customer

import io.javalin.Javalin

class App(val customerService: CustomerService) {
    private val javalin: Javalin = Javalin.create()

    init {
        javalin.get("/customers/:id") { ctx ->
            ctx.pathParam("id")
                    .toLongOrNull()
                    ?.let(::CustomerId)
                    ?.let(customerService::findBy)
                    ?.also { ctx.json(it) }
                    ?: ctx.status(404)
        }
    }

    fun start() {
        javalin.start()
    }

    fun stop() {
        javalin.stop()
    }
}