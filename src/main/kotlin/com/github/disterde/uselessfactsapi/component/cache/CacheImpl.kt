package com.github.disterde.uselessfactsapi.component.cache

import java.util.concurrent.ConcurrentHashMap

class CacheImpl<K : Any, V : Any> : Cache<K, V> {

    private val cache = ConcurrentHashMap<K, V>()

    override fun save(key: K, entity: V) {
        cache[key] = entity
    }

    override fun getBy(key: K): V {
        return cache[key] ?: throw NoSuchElementException("No element found for key $key")
    }

    override fun getAll(): Collection<V> {
        return cache.values.toSet()
    }

    override fun getEntries(): Map<K, V> {
        return cache.toMap()
    }
}