package nay.kirill.settings

import nay.kirill.healthcare.domain.Theme

sealed interface SettingsState {

    data object Loading : SettingsState

    data class Content(
        val selectedTheme: Theme
    ) : SettingsState

    data object Error : SettingsState

    fun copyContent(block: Content.() -> Content): SettingsState = when (this) {
        is Content -> block()
        else -> this
    }

}