package nay.kirill.settings

sealed interface SettingsEvent {

    data object UpdateAdaptiveTheme : SettingsEvent

    data object Back : SettingsEvent

}