package com.github.disterde.uselessfactsapi.domain

import kotlinx.serialization.Serializable

/**
 * Represents a version of a fact with its original fact text and the associated shortened URL.
 *
 * @property originalFact The original text of the fact.
 * @property shortenedUrl The shortened URL associated with the fact.
 */
@Serializable
data class ShortenedUrlFact(
    val originalFact: String,
    val shortenedUrl: String
)
