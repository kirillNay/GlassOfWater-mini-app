plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    setupPlatforms(
        platforms = listOf(Platform.JS),
        commonDeps = {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material)
            api(compose.ui)

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
        }
    )
}

compose.experimental {
    web.application {}
}
