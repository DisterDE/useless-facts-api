package com.github.disterde.uselessfactsapi.component.cache

import com.github.disterde.uselessfactsapi.exception.ElementAlreadyInCacheException
import com.github.disterde.uselessfactsapi.exception.NoElementInCacheException
import java.util.concurrent.ConcurrentHashMap

/**
 * An implementation of the `Cache` interface that provides a concurrent hashmap-based cache mechanism.
 *
 * @param K The type of keys maintained by this cache. The type must be non-null.
 * @param V The type of mapped values. The type must be non-null.
 */
class CacheImpl<K : Any, V : Any> : Cache<K, V> {

    /**
     * A concurrent hashmap used for caching key-value pairs.
     * Provides core storage functionality for the cache mechanism,
     * ensuring thread-safe read and write operations.
     *
     * @param K The type of keys maintained by this map. The type must be non-null.
     * @param V The type of mapped values. The type must be non-null.
     */
    private val cache = ConcurrentHashMap<K, V>()

    /**
     * Saves an entity in the cache associated with the given key.
     * If the key already exists in the cache, it throws an ElementAlreadyInCacheException.
     *
     * @param key The key to associate with the cached entity. Must be non-null.
     * @param entity The entity to be cached. Must be non-null.
     * @throws ElementAlreadyInCacheException if an element with the same key already exists in the cache.
     */
    override fun save(key: K, entity: V) {
        if (cache.containsKey(key)) throw ElementAlreadyInCacheException(key)
        cache[key] = entity
    }

    /**
     * Retrieves the value associated with the specified key from the cache.
     *
     * @param key The key whose associated value is to be returned. Must be non-null.
     * @return The value associated with the specified key.
     * @throws NoElementInCacheException if the key is not found in the cache.
     */
    override fun getBy(key: K): V {
        return cache[key] ?: throw NoElementInCacheException(key)
    }

    /**
     * Retrieves all values currently stored in the cache.
     *
     * @return A collection of all values in the cache.
     */
    override fun getAll(): Collection<V> {
        return cache.values.toSet()
    }

    /**
     * Retrieves all key-value pairs currently stored in the cache as a map.
     *
     * @return A map of all key-value pairs in the cache.
     */
    override fun getEntries(): Map<K, V> {
        return cache.toMap()
    }
}
