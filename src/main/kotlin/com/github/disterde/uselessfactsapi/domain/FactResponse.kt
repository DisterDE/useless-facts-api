package com.github.disterde.uselessfactsapi.domain

import kotlinx.serialization.Serializable

/**
 * Represents a response containing a fact and its original permalink.
 *
 * @property fact The factual information.
 * @property originalPermalink The permanent link associated with the fact.
 */
@Serializable
data class FactResponse(
    val fact: String,
    val originalPermalink: String
)