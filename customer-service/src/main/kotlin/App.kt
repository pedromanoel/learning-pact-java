package codes.pedromanoel.pact.customer

import io.javalin.Javalin

class App {
    private val javalin: Javalin = Javalin.create()

    fun start() {
        javalin.start()
    }

    fun stop() {
        javalin.stop()
    }
}