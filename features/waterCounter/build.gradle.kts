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
            implementation(project(":core:arch"))
            implementation(project(":core:ui"))

            implementation(project(":domain"))

            implementation("io.insert-koin:koin-core:3.2.0")
        },
        jsDeps = {
            implementation("io.github.kirillNay:tg-mini-app:1.0.0")
        }
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
