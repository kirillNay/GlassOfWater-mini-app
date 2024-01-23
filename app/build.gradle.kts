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
            api(compose.foundation)
            api(compose.material)
            api(compose.ui)

            implementation(libs.kotlinx.coroutines.core)

            implementation(project(":features:waterCounter"))
            implementation(project(":features:settings"))
            implementation(project(":domain"))
            implementation(project(":data"))
            implementation(project(":core:res"))
            implementation(project(":core:navigation"))

            implementation(libs.koin.core)

            implementation(libs.voyager.core)
            implementation(libs.voyager.navigator)
        },
        jsDeps = {
            implementation(libs.tg.miniApp)
        }
    )
}

compose.experimental {
    web.application {}
}
