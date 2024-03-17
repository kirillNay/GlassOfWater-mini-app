plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

group = "nay.kirill"
version = "1.0-SNAPSHOT"

kotlin {
    setupPlatforms(
        platforms = listOf(Platform.JS),
        commonDeps = {
            implementation(project(":domain"))
            implementation("io.github.kirillNay:tg-mini-app:1.0.0")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
        }
    )
}

