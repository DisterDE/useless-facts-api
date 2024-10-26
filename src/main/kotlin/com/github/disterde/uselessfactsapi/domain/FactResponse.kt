package com.github.disterde.uselessfactsapi.domain

import kotlinx.serialization.Serializable

@Serializable
data class FactResponse(
    val fact: String,
    val originalPermalink: String
)