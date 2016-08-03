package net.gouline.vertxexample

import io.vertx.core.AbstractVerticle
import io.vertx.core.Handler
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class CheatVerticle : AbstractVerticle() {

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    private fun createRouter() = Router.router(vertx).apply {
        get("/").handler(handlerRoot)
        get("/islands").handler(handlerIslands)
        get("/countries").handler(handlerCountries)
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    val handlerRoot = Handler<RoutingContext> { req ->
        req.response().end("Welcome!")
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    val handlerIslands = Handler<RoutingContext> { req ->
        req.response()
                .putHeader("Content-Type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(MOCK_ISLANDS))
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    val handlerCountries = Handler<RoutingContext> { req ->
        val countries = MOCK_ISLANDS.map { it.country }.distinct().sortedBy { it.code }

        req.response()
                .putHeader("Content-Type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(countries))
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    fun HttpServerResponse.endWithJson(obj: Any) {
        this.putHeader("Content-Type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(obj))
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    private val MOCK_ISLANDS = listOf<Island>()

}