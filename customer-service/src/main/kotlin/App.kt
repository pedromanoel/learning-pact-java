package codes.pedromanoel.pact.customer

import io.javalin.Javalin

class App(val customerService: CustomerService) {
    private val javalin: Javalin = Javalin.create()
    private val customerController = CustomerController(customerService)

    init {
        javalin.get("/customers/:id", customerController::getUser)
    }

    fun start() {
        javalin.start()
    }

    fun stop() {
        javalin.stop()
    }
}