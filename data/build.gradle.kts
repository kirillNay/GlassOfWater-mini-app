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
            implementation("com.kirillNay.telegram:mini-app:0.0.1")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
        }
    )
}

