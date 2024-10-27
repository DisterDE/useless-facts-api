package com.github.disterde.uselessfactsapi.component.cache

/**
 * A generic interface for a cache system that allows storing, retrieving,
 * and managing cache entries based on keys and values.
 *
 * @param K The type of keys maintained by this cache. The type must be non-null.
 * @param V The type of mapped values. The type must be non-null.
 */
interface Cache<K : Any, V : Any> {

    /**
     * Saves an entity in the cache associated with the given key.
     *
     * @param key The key to associate with the cached entity. Must be non-null.
     * @param entity The entity to be cached. Must be non-null.
     */
    fun save(key: K, entity: V)

    /**
     * Retrieves the value associated with the specified key from the cache.
     *
     * @param key The key whose associated value is to be returned. Must be non-null.
     * @return The value associated with the specified key, or throws NoElementInCacheException if the key is not found.
     */
    fun getBy(key: K): V

    /**
     * Retrieves all values currently stored in the cache.
     */
    fun getAll(): Collection<V>

    /**
     * Retrieves all key-value pairs currently stored in the cache as a map.
     *
     * @return A map of all key-value pairs in the cache.
     */
    fun getEntries(): Map<K, V>
}