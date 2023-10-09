package nay.kirill.settings

sealed interface SettingsState {

    data object Loading : SettingsState

    data class Content(
        val isAdaptiveBoolean: Boolean
    ) : SettingsState

    fun copyContent(block: Content.() -> Content): SettingsState = when (this) {
        is Content -> block()
        else -> this
    }

}