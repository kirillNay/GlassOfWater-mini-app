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
        implementation(project(":core:ui"))
        implementation(project(":core:navigation"))

        implementation(project(":domain"))

        implementation(libs.koin.core)
        implementation(libs.koin.compose)

        implementation(libs.voyager.koin)
        implementation(libs.voyager.navigator)
    },
    jsDeps = {
        implementation(libs.tg.miniApp)
    },
    androidDeps = {
        implementation(libs.lottie)

        implementation(libs.compose.preview)
        implementation(libs.compose.tooling)
    },
    androidNamespace = "nay.kirill.glassOfWater.features.counter"
)

compose.experimental {
    web.application {}
}
