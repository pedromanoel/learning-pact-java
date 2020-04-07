package codes.pedromanoel.pact.customer

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

class App(val customerService: CustomerService) {
    private val javalin: Javalin = Javalin.create()
    private val customerController = CustomerController(customerService)

    init {
        javalin.routes {
            path("customers") {
                path(":id") {
                    get(customerController::getUser)
                }
            }
        }
    }

    fun start() {
        javalin.start()
    }

    fun stop() {
        javalin.stop()
    }
}