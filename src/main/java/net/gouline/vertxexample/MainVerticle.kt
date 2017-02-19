package net.gouline.vertxexample

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.web.Router

@VerticleClass class MainVerticle : AbstractVerticle() {

    private val dao = IslandsDao()

    private val router = Router.router(vertx).apply {
        /**
         * Welcome handler.
         */
        get("/").handler { ctx ->
            ctx.response.end("Welcome!")
        }

        /**
         * Lists all islands.
         */
        get("/islands").handler { ctx ->
            val islands = dao.fetchIslands()
            ctx.response.endWithJson(islands)
        }

        /**
         * Lists all countries.
         */
        get("/countries").handler { ctx ->
            val countries = dao.fetchCountries()
            ctx.response.endWithJson(countries)
        }

        /**
         * Returns specific country.
         */
        get("/countries/:code").handler { ctx ->
            val code = ctx.request.getParam("code")
            val countries = dao.fetchCountries(code)

            if (countries.isEmpty()) {
                ctx.fail(404)
            } else {
                ctx.response.endWithJson(countries.first())
            }
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
