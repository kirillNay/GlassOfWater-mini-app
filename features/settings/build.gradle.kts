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

        implementation(libs.voyager.core)
        implementation(libs.voyager.koin)
    },
    jsDeps = {
        implementation(libs.tg.miniApp)
    },
    androidNamespace = "nay.kirill.glassOfWater.features.settings"
)

compose.experimental {
    web.application {}
}
