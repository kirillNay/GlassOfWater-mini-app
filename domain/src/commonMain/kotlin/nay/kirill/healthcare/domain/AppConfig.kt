package nay.kirill.healthcare.domain

import kotlinx.serialization.Serializable

@Serializable
data class AppConfig(
    val isAdaptiveTheme: Boolean,
) {

    companion object {

        val default = AppConfig(
            isAdaptiveTheme = false
        )

    }

}