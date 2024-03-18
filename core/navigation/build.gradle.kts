plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
}

setupMuliplatformProject(
    platforms = listOf(Platform.JS, Platform.ANDROID),
    commonDeps = {
        api(compose.dependencies.runtime)
        api(compose.dependencies.runtimeSaveable)

        implementation(libs.voyager.core)
        implementation(libs.voyager.navigator)

        implementation(libs.kotlinx.coroutines.core)
    },
    androidNamespace = "nay.kirill.glassOfWater.navigation"
)
