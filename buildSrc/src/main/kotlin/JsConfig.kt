import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun KotlinMultiplatformExtension.setupJsTarget() {
    js(IR) {
        moduleName = "glassOfWater"
        browser {
            commonWebpackConfig {
                outputFileName = "glassOfWater.js"
            }
        }
        binaries.executable()
    }
}