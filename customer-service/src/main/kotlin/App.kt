package codes.pedromanoel.pact.customer

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

class App(val customerService: CustomerService) {
    private val javalin: Javalin = Javalin.create()
    private val customerController = CustomerController(customerService)

    init {
        javalin.routes {
            crud("customers/:id", customerController)
        }
    }

    fun start() {
        javalin.start()
    }

    fun stop() {
        javalin.stop()
    }
}