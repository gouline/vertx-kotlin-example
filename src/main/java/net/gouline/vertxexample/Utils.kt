package net.gouline.vertxexample

import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.Json

/**
 * Ends request with JSON-serialized object.
 */
fun HttpServerResponse.endWithJson(obj: Any) {
    putHeader("Content-Type", "application/json; charset=utf-8").end(Json.encodePrettily(obj))
}
