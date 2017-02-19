package net.gouline.vertxexample

import io.vertx.core.http.HttpServerRequest
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.Json
import io.vertx.ext.web.RoutingContext

/**
 * Ends request with JSON-serialized object.
 */
fun HttpServerResponse.endWithJson(obj: Any) {
    putHeader("Content-Type", "application/json; charset=utf-8").end(Json.encodePrettily(obj))
}

/**
 * Shorthand for [RoutingContext.request].
 */
val RoutingContext.request: HttpServerRequest get() = request()

/**
 * Shorthand for [RoutingContext.response].
 */
val RoutingContext.response: HttpServerResponse get() = response()

/**
 * Source annotation for verticles to bind unused rules to.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class VerticleClass
