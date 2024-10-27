package com.github.disterde.uselessfactsapi.exception

/**
 * Exception thrown to indicate that validation of some input has failed.
 *
 * This exception is typically used in situations where input values do not
 * meet the expected criteria. It extends [ApiException], which is a custom
 * exception for API-related errors.
 *
 * @constructor
 * Constructs a new `ValidationException` with the specified detail message.
 *
 * @param message The detail message indicating the reason for the exception.
 *
 * @constructor
 * Constructs a new `ValidationException` with the specified detail message
 * and cause.
 *
 * @param message The detail message indicating the reason for the exception.
 * @param cause The cause of the exception, which allows chaining of exceptions.
 */
class ValidationException : ApiException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}