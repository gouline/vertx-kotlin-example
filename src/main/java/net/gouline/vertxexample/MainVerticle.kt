package net.gouline.vertxexample

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

@Suppress("unused")
class MainVerticle : AbstractVerticle() {

    companion object {
        private val MOCK_ISLANDS by lazy {
            listOf(
                    Island("Kotlin", Country("Russia", "RU")),
                    Island("Stewart Island", Country("New Zealand", "NZ")),
                    Island("Cockatoo Island", Country("Australia", "AU")),
                    Island("Tasmania", Country("Australia", "AU"))
            )
        }
    }

    override fun start(startFuture: Future<Void>?) {
        val router = Router.router(vertx).apply {
            val v = this@MainVerticle

            get("/").handler(v::handleRoot)
            get("/islands").handler(v::handleListIslands)
            get("/countries").handler(v::handleListCountries)
        }

        vertx.createHttpServer()
                .requestHandler { router.accept(it) }
                .listen(Integer.getInteger("http.port", 8080)) { result ->
                    if (result.succeeded()) {
                        startFuture?.complete()
                    } else {
                        startFuture?.fail(result.cause())
                    }
                }
    }

    private fun handleRoot(context: RoutingContext) {
        context.response().end("Welcome!")
    }

    private fun handleListIslands(context: RoutingContext) {
        context.response().endWithJson(MOCK_ISLANDS)
    }

    private fun handleListCountries(context: RoutingContext) {
        val countries = MOCK_ISLANDS.map { it.country }.distinct().sortedBy { it.code }
        context.response().endWithJson(countries)
    }

}
