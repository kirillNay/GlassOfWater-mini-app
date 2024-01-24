group = "nay.kirill"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }

    dependencies {
        classpath(libs.plugin.multiplatform.compose)
        classpath(libs.plugin.kotlinx.serialization)
    }
}
