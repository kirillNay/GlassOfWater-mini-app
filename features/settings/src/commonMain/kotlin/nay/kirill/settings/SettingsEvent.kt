package nay.kirill.settings

import nay.kirill.healthcare.domain.Theme

sealed interface SettingsEvent {

    data class SetTheme(val theme: Theme) : SettingsEvent

    data object Back : SettingsEvent

}