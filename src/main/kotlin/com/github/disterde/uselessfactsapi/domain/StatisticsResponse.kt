package com.github.disterde.uselessfactsapi.domain

import kotlinx.serialization.Serializable

@Serializable
data class StatisticsResponse(
    val shortenedUrl: String,
    val accessCount: Int
)