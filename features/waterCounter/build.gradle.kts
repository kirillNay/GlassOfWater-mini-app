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

            implementation(project(":core:res"))
            implementation(project(":core:arch"))

            implementation(project(":domain"))

            implementation("io.insert-koin:koin-core:3.2.0")
        },
        jsDeps = {
            implementation("com.kirillNay.telegram:mini-app:0.0.1")
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
