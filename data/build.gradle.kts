plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

group = "nay.kirill"
version = "1.0-SNAPSHOT"

setupMuliplatformProject(
    platforms = listOf(Platform.JS, Platform.ANDROID),
    commonDeps = {
        implementation(project(":domain"))

        implementation(libs.kotlinx.coroutines.core)
        implementation(libs.kotlinx.serialization.json)
    },
    jsDeps = {
        implementation(libs.tg.miniApp)
    }
)
