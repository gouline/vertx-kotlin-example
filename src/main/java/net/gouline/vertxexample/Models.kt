package net.gouline.vertxexample

/**
 * Country model.
 *
 * @param name Full country name.
 * @param code ISO 3166 country code.
 */
data class Country(val name: String, val code: String)

/**
 * Island model.
 *
 * @param name Full island name.
 * @param country Country associated with the island.
 */
data class Island(val name: String, val country: Country)
