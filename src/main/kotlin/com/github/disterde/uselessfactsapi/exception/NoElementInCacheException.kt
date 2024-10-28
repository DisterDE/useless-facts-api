package com.github.disterde.uselessfactsapi.exception

/**
 * Exception thrown when an element is not found in the cache for the specified key.
 *
 * This exception is used to indicate that there is no value associated with the
 * given key in the cache, and therefore the requested operation cannot be performed.
 *
 * @param key The key for which the element was not found in the cache.
 */
class NoElementInCacheException(
    key: Any
) : ApiException("Element is not found in cache by $key")
