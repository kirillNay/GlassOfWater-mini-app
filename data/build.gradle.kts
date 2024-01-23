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

            implementation(libs.tg.miniApp)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
        }
    )
}

