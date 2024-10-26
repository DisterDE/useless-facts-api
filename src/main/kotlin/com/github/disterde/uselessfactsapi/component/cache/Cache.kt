package com.github.disterde.uselessfactsapi.component.cache

interface Cache<K : Any, V : Any> {
    fun save(key: K, entity: V)
    fun getBy(key: K): V
    fun getAll(): Collection<V>
    fun getEntries(): Map<K, V>
}