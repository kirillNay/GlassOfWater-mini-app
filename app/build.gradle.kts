plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

group = "nay.kirill"
version = "1.0-SNAPSHOT"

setupMuliplatformProject(
    platforms = listOf(Platform.JS, Platform.ANDROID),
    commonDeps = {
        api(compose.dependencies.material)
        api(compose.dependencies.runtime)

        implementation(libs.kotlinx.coroutines.core)

        implementation(project(":features:waterCounter"))
        implementation(project(":features:settings"))
        implementation(project(":domain"))
        implementation(project(":data"))
        implementation(project(":core:res"))
        implementation(project(":core:navigation"))
        implementation(project(":core:ui"))

        implementation(libs.koin.core)

        implementation(libs.voyager.core)
        implementation(libs.voyager.navigator)
    },
    jsDeps = {
        implementation(libs.tg.miniApp)
    },
    androidDeps = {
        implementation(libs.koin.android)

        implementation(libs.appCompat)
        implementation(libs.compose.activity)
    }
)

compose.experimental {
    web.application {}
}

// Android configuration
android {
    namespace = "nay.kirill.glassOfWater"
    defaultConfig {
        applicationId = "nay.kirill.glassOfWater"
    }
}
