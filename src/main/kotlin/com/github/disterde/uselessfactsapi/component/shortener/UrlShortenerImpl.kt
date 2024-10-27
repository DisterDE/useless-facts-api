package com.github.disterde.uselessfactsapi.component.shortener

import com.github.disterde.uselessfactsapi.constants.UrlConstants.FACTS_BASE_PATH
import org.apache.commons.lang3.RandomStringUtils

/**
 * Implementation of the UrlShortener interface for generating shortened URLs.
 *
 * This class uses a secure random alphanumeric string generator for creating
 * short URL paths, and appends the generated string to a base path.
 */
class UrlShortenerImpl : UrlShortener {

    /**
     * Secure random alphanumeric string generator used for creating short URL paths.
     *
     * This generator is used in the process of generating shortened URLs to ensure
     * uniqueness and unpredictability in the URL paths. It leverages a secure random
     * algorithm to produce strings that are suitable for use in generating short URLs.
     */
    private val random = RandomStringUtils.secure()

    /**
     * Generates and returns a shortened URL.
     *
     * @return A string representing the shortened URL.
     */
    override fun getShortUrl(): String {
        val shortenedUrl = random.nextAlphanumeric(LINK_LENGTH)
        return "$FACTS_BASE_PATH/$shortenedUrl"
    }

    companion object {
        /**
         * The length of the alphanumeric string to be generated for creating short URL paths.
         *
         * This constant is used when generating a shortened URL to determine the size of
         * the random string appended to the base URL, ensuring a fixed and predictable length.
         */
        const val LINK_LENGTH = 5
    }
}