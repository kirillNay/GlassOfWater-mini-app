package android

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun LibraryExtension.setupLibrary(
    target: Project,
    targetPackage: String
) {
    compileSdk = AppConfig.compileSdk

    defaultConfig.apply {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        namespace = targetPackage
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    target.tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}