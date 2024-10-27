val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project
val mockk_version: String by project
val commons_lang_version: String by project

plugins {
    kotlin("jvm") version "2.0.21"
    id("io.ktor.plugin") version "3.0.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"
}

group = "com.github.disterde"
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
    //Ktor
    implementation("io.ktor:ktor-server-cio")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-client-cio")
    implementation("io.ktor:ktor-client-core")
    implementation("io.ktor:ktor-client-content-negotiation")
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-status-pages")
    implementation("io.ktor:ktor-server-call-logging")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-server-swagger")

    // Koin
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")

    // Utils
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.apache.commons:commons-lang3:$commons_lang_version")

    // Test
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation("io.mockk:mockk:${mockk_version}")
}

kotlin {
    jvmToolchain(21)
}