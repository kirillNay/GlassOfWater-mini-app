plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "nay.kirill"
version = "1.0-SNAPSHOT"

kotlin {
    setupPlatforms(
        platforms = listOf(Platform.JS),
        commonDeps = {
            api(compose.runtime)

            implementation(libs.voyager.core)
            implementation(libs.voyager.navigator)

            implementation(libs.kotlinx.coroutines.core)
        }
    )
}

