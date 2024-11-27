plugins {
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.versions)
    alias(libs.plugins.versions.catalog)
    id("jacoco")
}

group = "com.github.disterde.uselessfactsapi"
version = "1.0.0"

application {
    mainClass.set("com.github.disterde.uselessfactsapi.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor Client
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)

    // Ktor Server
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.swagger)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)

    // Utils
    implementation(libs.logback.classic)
    implementation(libs.commons.lang3)

    // Test
    testImplementation(libs.mockk)
    testImplementation(libs.assertj.core)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.ktor.server.test.host)
}

kotlin {
    jvmToolchain(21)
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.test { finalizedBy(tasks.jacocoTestReport) }

tasks.jacocoTestReport {
    classDirectories.setFrom(
        files(
            classDirectories.files.map {
                fileTree(it) {
                    exclude("**/domain/**")
                    exclude("**/plugins/**")
                    exclude("**/constants/**")
                    exclude("**/exception/**")
                    exclude("**/Application*")
                }
            }
        )
    )
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = 0.99.toBigDecimal()
            }
        }
    }

    classDirectories.setFrom(
        files(
            classDirectories.files.map {
                fileTree(it) {
                    exclude("**/domain/**")
                    exclude("**/plugins/**")
                    exclude("**/constants/**")
                    exclude("**/exception/**")
                    exclude("**/Application*")
                }
            }
        )
    )
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}

tasks.dependencyUpdates {
    rejectVersionIf {
        candidate.version.isNonStable()
    }
}

fun String.isNonStable(): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return isStable.not()
}