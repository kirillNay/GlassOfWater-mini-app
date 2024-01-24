package android

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun BaseExtension.androidApp(
    target: Project,
    targetNamespace: String?
) {
    compileSdkVersion(AppConfig.compileSdk)

    defaultConfig.apply {
        applicationId = AppConfig.applicationId
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        if (targetNamespace != null) namespace = targetNamespace
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

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures.apply {
        compose = true
    }

    target.tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}