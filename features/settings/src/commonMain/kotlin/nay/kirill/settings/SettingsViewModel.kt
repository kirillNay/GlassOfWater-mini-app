package nay.kirill.settings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.kirillNay.telegram.miniapp.webApp.webApp
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

    init {
        launch {
            val config = getAppConfigUseCase()
            _state.value = SettingsState.Content(
                isAdaptiveBoolean = config.isAdaptiveTheme
            )
        }

        webApp.backButton
            .onClick {
                screenModelScope.launch { navigation.back() }
            }
            .show()
    }

    fun clearData(confirmationText: String) {
        webApp.showConfirm(confirmationText) { result ->
            if (result) {
                launch {
                    clearParamsUseCase()
                }
            }
        }
    }

    fun mockData(confirmationText: String) {
        webApp.showConfirm(confirmationText) { result ->
            if (result) {
                launch {
                    mockParamsUseCase()
                }
            }
        }
    }

    fun updateAdaptiveTheme() {
        _state.value = _state.value.copyContent { copy(isAdaptiveBoolean = !isAdaptiveBoolean) }
        updateConfig()
    }

    private fun updateConfig() {
        (_state.value as? SettingsState.Content)?.let {
            launch {
                saveAppConfigUseCase(
                    AppConfig(
                        isAdaptiveTheme = it.isAdaptiveBoolean
                    )
                )
            }
        }
    }

    override fun onDispose() {
        super.onDispose()
        webApp.backButton
            .offClick {
                screenModelScope.launch { navigation.back() }
            }
            .hide()
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