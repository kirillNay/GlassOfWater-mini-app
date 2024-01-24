plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
}

setupMuliplatformProject(
    platforms = listOf(Platform.JS, Platform.ANDROID),
    commonDeps = {
        api(compose.dependencies.runtime)
        api(compose.dependencies.material)
        api(compose.dependencies.ui)

        implementation(project(":core:res"))
    },
    androidNamespace = "nay.kirill.glassOfWater.ui"
)

compose.experimental {
    web.application {}
}
