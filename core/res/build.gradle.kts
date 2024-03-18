plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
}

group = "nay.kirill"
version = "1.0-SNAPSHOT"

setupMuliplatformProject(
    platforms = listOf(Platform.JS, Platform.ANDROID),
    commonDeps = {
        api(compose.dependencies.runtime)
        api(compose.dependencies.material)
        api(compose.dependencies.ui)

        api(compose.dependencies.material3)
    },
    androidNamespace = "nay.kirill.glassOfWater.res"
)

compose.experimental {
    web.application {}
}
