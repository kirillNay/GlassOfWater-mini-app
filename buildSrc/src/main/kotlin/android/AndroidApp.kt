package android

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun BaseAppModuleExtension.androidApp(
    target: Project,
    targetPackage: String
) {
    defaultConfig.apply {
        applicationId = AppConfig.applicationId
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        namespace = targetPackage
    }

    buildTypes.apply {
        getByName("release").apply {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug").apply {
            isMinifyEnabled = true
            isDebuggable = true
        }
    }

    compileSdk = AppConfig.compileSdk

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