package com.github.disterde.uselessfactsapi.domain

import kotlinx.serialization.Serializable

@Serializable
data class Fact(
    val text: String,
    val permalink: String
) {
    fun toResponse() = FactResponse(text, permalink)
    fun toShortenedResponse(shortenedUrl: String) = ShortenedUrlFact(text, shortenedUrl)
}

