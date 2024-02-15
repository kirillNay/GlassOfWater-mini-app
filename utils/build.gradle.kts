plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

group = "nay.kirill"
version = "1.0-SNAPSHOT"

setupMuliplatformProject(
    platforms = listOf(Platform.JS, Platform.ANDROID),
    commonDeps = {
        implementation(libs.kotlinx.datetime)
    },
    androidNamespace = "nay.kirill.glassOfWater.utils"
)
