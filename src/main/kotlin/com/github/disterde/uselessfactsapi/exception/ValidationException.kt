package com.github.disterde.uselessfactsapi.exception

/**
 * Exception thrown when a validation error occurs.
 *
 * This exception is used to indicate that the input provided by the user
 * does not pass the defined validation rules. It extends [ApiException],
 * thereby making it a specific type of API-related error.
 *
 * @param message The detail message explaining the validation error.
 * @param cause The cause of the validation error.
 */
class ValidationException(message: String, cause: Throwable) : ApiException(message, cause)
