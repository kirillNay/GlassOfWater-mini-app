package android

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun KotlinMultiplatformExtension.setupAndroidTarget() {
    androidTarget()
}

fun Project.setupAndroid(nameSpace: String? = null) {
    findAndroidExtension().apply {
        sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

        if (plugins.findPlugin("com.android.application") != null) {
            androidApp(target = this@setupAndroid, nameSpace)
        } else if (plugins.findPlugin("com.android.library") != null) {
            androidLibrary(target = this@setupAndroid, nameSpace)
        }
    }
}

private fun Project.findAndroidExtension(): BaseExtension = extensions.findByType<LibraryExtension>()
    ?: extensions.findByType<com.android.build.gradle.AppExtension>()
    ?: error("Could not found Android application or library plugin applied on module $name")