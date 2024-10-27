package com.github.disterde.uselessfactsapi.domain

import kotlinx.serialization.Serializable

/**
 * Represents an immutable fact with its associated permanent link.
 *
 * @property text The factual information as a string.
 * @property permalink The permanent link associated with the fact.
 */
@Serializable
data class Fact(
    val text: String,
    val permalink: String
) {
    /**
     * Converts the current `Fact` object to a `FactResponse`.
     *
     * @return A `FactResponse` object containing the fact text and its associated permanent link.
     */
    fun toResponse() = FactResponse(text, permalink)

    /**
     * Converts the current `Fact` object to a `ShortenedUrlFact`.
     *
     * @param shortenedUrl The shortened URL to associate with the fact.
     * @return A `ShortenedUrlFact` object containing the fact text and the shortened URL.
     */
    fun toShortenedResponse(shortenedUrl: String) = ShortenedUrlFact(text, shortenedUrl)
}

