package com.github.disterde.uselessfactsapi.domain

import kotlinx.serialization.Serializable

/**
 * Data class that represents a response containing statistics about a shortened URL.
 *
 * @property shortenedUrl The shortened URL.
 * @property accessCount The number of times the shortened URL has been accessed.
 */
@Serializable
data class StatisticsResponse(
    val shortenedUrl: String,
    val accessCount: Int
)
