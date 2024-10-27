package com.github.disterde.uselessfactsapi.component.shortener

/**
 * Interface for URL shortening functionality.
 */
interface UrlShortener {

    /**
     * Generates and returns a shortened URL.
     *
     * @return A string representing the shortened URL.
     */
    fun getShortUrl(): String
}