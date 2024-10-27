package com.github.disterde.uselessfactsapi.domain

import kotlinx.serialization.Serializable

/**
 * Represents a response containing a fact and its original permalink.
 *
 * @property fact The factual information.
 * @property originalPermalink The permanent link associated with the fact.
 */
@Serializable
data class Fact(
    val fact: String,
    val originalPermalink: String
) {
    /**
     * Converts the current `Fact` object to a `ShortenedUrlFact`.
     *
     * @param shortenedUrl The shortened URL to associate with the fact.
     * @return A `ShortenedUrlFact` object containing the fact text and the shortened URL.
     */
    fun toShortenedUrlFact(shortenedUrl: String) = ShortenedUrlFact(fact, shortenedUrl)
}