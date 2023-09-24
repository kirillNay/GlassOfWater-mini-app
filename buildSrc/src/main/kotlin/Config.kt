import android.setupAndroidTarget
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun KotlinMultiplatformExtension.setupPlatforms(
    platforms: List<Platform>,
    commonDeps: KotlinDependencyHandler.() -> Unit,
    androidDeps: (KotlinDependencyHandler.() -> Unit)? = null,
    jsDeps: (KotlinDependencyHandler.() -> Unit)? = null,
) {
    platforms.forEach { platform ->
        when (platform) {
            Platform.ANDROID -> setupAndroidTarget()
            Platform.JS -> setupJsTarget()
        }
    }

    (this as ExtensionAware).extensions.configure<NamedDomainObjectContainer<KotlinSourceSet>>("sourceSets") {
        val commonMain = getByName("commonMain") {
            dependencies {
                commonDeps()
            }
        }
        platforms.forEach { platform ->
            when (platform) {
                Platform.ANDROID -> getByName("androidMain") {
                    dependsOn(commonMain)
                    dependencies {
                        androidDeps?.invoke(this)
                    }
                }
                Platform.JS -> getByName("jsMain") {
                    dependsOn(commonMain)
                    dependencies {
                        jsDeps?.invoke(this)
                    }
                }
            }
        }
    }
}

enum class Platform {
    ANDROID, JS, // TODO add more platforms
}

