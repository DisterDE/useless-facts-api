package com.github.disterde.uselessfactsapi.constants

/**
 * Object holds the constant values related to URLs used within the application.
 */
object UrlConstants {
    /**
     * Base path for routing fact-related HTTP requests.
     *
     * This constant defines the root URL path segment for endpoints
     * that handle various fact operations, such as retrieving cached
     * facts, fetching random facts, or getting facts by their shortened URLs.
     */
    const val FACTS_BASE_PATH = "/facts"

    /**
     * The base URL for accessing external facts from the Useless Facts API.
     */
    private const val EXTERNAL_FACTS_URL = "https://uselessfacts.jsph.pl/api/v2/facts"

    /**
     * The URL used to retrieve a random fact in English from the external facts API.
     */
    const val EXTERNAL_RANDOM_FACT_URL = "$EXTERNAL_FACTS_URL/random?language=en"
}