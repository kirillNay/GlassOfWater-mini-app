package nay.kirill.settings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.healthcare.domain.AppConfig
import nay.kirill.healthcare.domain.useCases.ClearParamsUseCase
import nay.kirill.healthcare.domain.useCases.GetAppConfigUseCase
import nay.kirill.healthcare.domain.useCases.MockParamsUseCase
import nay.kirill.healthcare.domain.useCases.SaveAppConfigUseCase

class SettingsViewModel(
    private val getAppConfigUseCase: GetAppConfigUseCase,
    private val saveAppConfigUseCase: SaveAppConfigUseCase,
    private val clearParamsUseCase: ClearParamsUseCase,
    private val mockParamsUseCase: MockParamsUseCase,
    private val navigation: Navigation
) : ScreenModel {

    private val _state = MutableStateFlow<SettingsState>(SettingsState.Loading)
    val state: StateFlow<SettingsState> = _state

    fun accept(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SetTheme -> updateAdaptiveTheme(event)
            is SettingsEvent.Back -> launch {  navigation.back() }
        }
    }

    init {
        launch {
            val config = getAppConfigUseCase()
            _state.value = SettingsState.Content(
                selectedTheme = config.selectedTheme
            )
        }

//        webApp.backButton
//            .onClick {
//                screenModelScope.launch { navigation.back() }
//            }
//            .show()
    }

    private fun updateAdaptiveTheme(event: SettingsEvent.SetTheme) {
        _state.value = _state.value.copyContent { copy(selectedTheme = event.theme) }
        updateConfig()
    }

    private fun updateConfig() {
        (_state.value as? SettingsState.Content)?.let {
            launch {
                saveAppConfigUseCase(AppConfig(selectedTheme = it.selectedTheme))
            }
        }
    }

    override fun onDispose() {
        super.onDispose()
//        webApp.backButton
//            .offClick {
//                screenModelScope.launch { navigation.back() }
//            }
//            .hide()
    }

    private fun onError() {
        _state.value = SettingsState.Error
    }

    private fun launch(block: suspend () -> Unit) {
        screenModelScope.launch(context = CoroutineExceptionHandler { _, _ -> onError() }) {
            block()
        }
    }

}