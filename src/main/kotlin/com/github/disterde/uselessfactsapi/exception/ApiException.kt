package com.github.disterde.uselessfactsapi.exception

/**
 * Represents a custom exception used for handling API-related errors.
 *
 * This class serves as the base class for different specific exceptions
 * that can occur during API interactions. It extends [RuntimeException],
 * thereby allowing for unchecked exceptions.
 */
open class ApiException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}