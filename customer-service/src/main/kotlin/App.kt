import io.javalin.Javalin

fun main() {
    val app = Javalin.create().start()

    app.get("customers/<customer-id>") {
        ctx -> ctx.resultFuture()
    }
}