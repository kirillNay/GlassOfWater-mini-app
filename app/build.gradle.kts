import android.androidApp

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.application")
}

group = "nay.kirill"
version = "1.0-SNAPSHOT"

kotlin {
    setupPlatforms(
        platforms = listOf(Platform.JS, Platform.ANDROID),
        commonDeps = {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material)
            api(compose.ui)

            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.material3)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
        },
        androidDeps = {
            implementation("androidx.appcompat:appcompat:1.5.1")
            implementation("androidx.core:core-ktx:1.7.0")

            implementation(platform("androidx.compose:compose-bom:2022.10.00"))
            implementation("androidx.compose.ui:ui-tooling")
            implementation("androidx.compose.ui:ui-tooling-preview")
            implementation("androidx.activity:activity-compose:1.6.1")

            implementation("com.airbnb.android:lottie-compose:6.1.0")
        }
    )
}

android {
    androidApp(
        target = project,
        targetPackage = "nay.kirill.glassOfWater"
    )
}

compose.experimental {
    web.application {}
}

compose {
    val composeVersion = project.property("compose.wasm.version") as String
    kotlinCompilerPlugin.set(composeVersion)
    val kotlinVersion = project.property("kotlin.version") as String
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=$kotlinVersion")
}
