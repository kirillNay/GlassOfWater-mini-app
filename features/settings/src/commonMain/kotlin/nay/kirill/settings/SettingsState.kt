package nay.kirill.settings

import nay.kirill.healthcare.domain.Theme

sealed interface SettingsState {

    data object Loading : SettingsState

    data class Content(
        val selectedTheme: Theme,
        val listOfThemes: List<ThemeItem>
    ) : SettingsState

    data object Error : SettingsState

    fun copyContent(block: Content.() -> Content): SettingsState = when (this) {
        is Content -> block()
        else -> this
    }

}

data class ThemeItem(
    val theme: Theme,
    val titleId: Int
)