package net.gouline.vertxexample

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.web.Router

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

    private val router = Router.router(vertx).apply {
        get("/").handler { context ->
            context.response().end("Welcome!")
        }

        get("/islands").handler { context ->
            context.response().endWithJson(MOCK_ISLANDS)
        }

        get("/countries").handler { context ->
            val countries = MOCK_ISLANDS.map { it.country }.distinct().sortedBy { it.code }
            context.response().endWithJson(countries)
        }
    }

    override fun start(startFuture: Future<Void>?) {
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

}
