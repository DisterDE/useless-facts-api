package com.github.disterde.uselessfactsapi.domain

import java.util.concurrent.atomic.AtomicInteger

class CountedEntity<T : Any>(private val entity: T) {
    private val accessCount = AtomicInteger(0)

    fun get(countAccess: Boolean = true) =
        entity.also { if (countAccess) accessCount.incrementAndGet() }

    fun getAccessCount() = accessCount.get()
}