package com.github.disterde.uselessfactsapi.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*

/**
 * Configures the security for the application using basic authentication.
 *
 * This function sets up the Authentication feature with a basic authentication provider.
 * If the credentials match, a [UserIdPrincipal] is returned; otherwise, null is returned.
 */
fun Application.configureSecurity() {
    install(Authentication) {
        basic {
            realm = "Access to the '/admin' path"
            validate { credentials ->
                if (credentials.name == "admin" && credentials.password == "admin") {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }
}
