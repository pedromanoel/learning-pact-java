package codes.pedromanoel.pact.customerservice

import io.javalin.Javalin

class App {
    private val javalin: Javalin = Javalin.create()

    fun start() {
        javalin.start()
    }
}