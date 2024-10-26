package com.github.disterde.uselessfactsapi.domain

import kotlinx.serialization.Serializable

@Serializable
data class ShortenedUrlFact(
    val originalFact: String,
    val shortenedUrl: String
)