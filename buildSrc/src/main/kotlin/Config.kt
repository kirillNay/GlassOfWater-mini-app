import android.setupAndroidTarget
import android.setupAndroid
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.setupMuliplatformProject(
    platforms: List<Platform>,
    commonDeps: KotlinDependencyHandler.() -> Unit,
    androidDeps: (KotlinDependencyHandler.() -> Unit)? = null,
    jsDeps: (KotlinDependencyHandler.() -> Unit)? = null,
    androidNamespace: String? = null
) {
    plugins.withType<org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper> {
        extensions.configure<KotlinMultiplatformExtension> {
            setupPlatforms(platforms, commonDeps, androidDeps, jsDeps)

            androidTarget {
                publishAllLibraryVariants()
            }

            sourceSets {
                all {
                    languageSettings.optIn("cafe.adriel.voyager.core.annotation.InternalVoyagerApi")
                    languageSettings.optIn("cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi")
                }
            }
        }

        if (platforms.contains(Platform.ANDROID)) setupAndroid(androidNamespace)

        tasks.withType<KotlinCompile> {
            kotlinOptions.configureKotlinJvmOptions()
        }
    }
}

private fun KotlinMultiplatformExtension.setupPlatforms(
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

private fun KotlinJvmOptions.configureKotlinJvmOptions() {
    jvmTarget = JavaVersion.VERSION_11.toString()

    freeCompilerArgs += "-opt-in=cafe.adriel.voyager.core.annotation.InternalVoyagerApi"
    freeCompilerArgs += "-opt-in=cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi"
}

enum class Platform {
    ANDROID, JS
}

