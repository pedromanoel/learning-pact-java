package codes.pedromanoel.pact.customer

import io.javalin.Javalin
import io.javalin.http.Context

class App(val customerService: CustomerService) {
    private val javalin: Javalin = Javalin.create()

    init {
        javalin.get("/customers/:id") { ctx: Context ->
            CustomerController(ctx, customerService).get()
        }
    }

    fun start() {
        javalin.start()
    }

    fun stop() {
        javalin.stop()
    }
}