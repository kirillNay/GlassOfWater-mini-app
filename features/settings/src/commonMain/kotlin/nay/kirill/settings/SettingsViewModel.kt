package nay.kirill.settings

import com.kirillNay.telegram.miniapp.webApp.webApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.clearDataConfirmation
import nay.kirill.glassOfWater.res.stringResource
import nay.kirill.healthcare.domain.AppConfig
import nay.kirill.healthcare.domain.useCases.ClearParamsUseCase
import nay.kirill.healthcare.domain.useCases.GetAppConfigUseCase
import nay.kirill.healthcare.domain.useCases.MockParamsUseCase
import nay.kirill.healthcare.domain.useCases.SaveAppConfigUseCase
import nay.kirill.kmpArch.ViewModel
import nay.kirill.kmpArch.navigation.NavigationStack

class SettingsViewModel(
    private val getAppConfigUseCase: GetAppConfigUseCase,
    private val saveAppConfigUseCase: SaveAppConfigUseCase,
    private val clearParamsUseCase: ClearParamsUseCase,
    private val mockParamsUseCase: MockParamsUseCase,
    private val navigationStack: NavigationStack
) : ViewModel() {

    private val _state = MutableStateFlow<SettingsState>(SettingsState.Loading)
    val state: StateFlow<SettingsState> = _state

    private val back = {
        navigationStack.back()
        onCleared()
    }

    init {
        viewModelScope.launch {
            val config = getAppConfigUseCase()
            _state.value = SettingsState.Content(
                isAdaptiveBoolean = config.isAdaptiveTheme
            )
        }

        webApp.backButton
            .onClick(back)
            .show()
    }

    fun clearData(confirmationText: String) {
        webApp.showConfirm(confirmationText) { result ->
            if (result) {
                viewModelScope.launch {
                    clearParamsUseCase()
                }
            }
        }
    }

    fun mockData(confirmationText: String) {
        webApp.showConfirm(confirmationText) { result ->
            if (result) {
                viewModelScope.launch {
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
            viewModelScope.launch {
                saveAppConfigUseCase(
                    AppConfig(
                        isAdaptiveTheme = it.isAdaptiveBoolean
                    )
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        webApp.backButton.offClick(back).hide()
    }

}