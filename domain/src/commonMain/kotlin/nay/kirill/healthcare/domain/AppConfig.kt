package nay.kirill.healthcare.domain

import kotlinx.serialization.Serializable

@Serializable
data class AppConfig(
    val selectedTheme: Theme,
) {

    companion object {

        val default = AppConfig(
            selectedTheme = Theme.SYSTEM
        )

    }

}