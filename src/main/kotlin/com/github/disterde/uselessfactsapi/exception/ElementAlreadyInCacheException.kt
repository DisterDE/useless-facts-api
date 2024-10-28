package com.github.disterde.uselessfactsapi.exception

/**
 * Exception thrown when attempting to add an element to the cache that already exists.
 *
 * This exception is used to indicate that the key for the element being
 * inserted into the cache already exists, and therefore the cache will
 * not be updated with the new element.
 *
 * @param key The key associated with the element that is already in the cache.
 */
class ElementAlreadyInCacheException(
    key: Any
) : ApiException("Element with key $key is already in cache")
