package nay.kirill.settings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.themeAdaptive
import nay.kirill.glassOfWater.res.themeDark
import nay.kirill.glassOfWater.res.themeLight
import nay.kirill.glassOfWater.res.themeSystem
import nay.kirill.glassOfWater.ui.theme.isAdaptiveThemeAvailable
import nay.kirill.healthcare.domain.AppConfig
import nay.kirill.healthcare.domain.Theme
import nay.kirill.healthcare.domain.useCases.GetAppConfigUseCase
import nay.kirill.healthcare.domain.useCases.SaveAppConfigUseCase

class SettingsViewModel(
    private val getAppConfigUseCase: GetAppConfigUseCase,
    private val saveAppConfigUseCase: SaveAppConfigUseCase,
    private val navigation: Navigation
) : ScreenModel {

    private val _state = MutableStateFlow<SettingsState>(SettingsState.Loading)
    val state: StateFlow<SettingsState> = _state

    fun accept(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SetTheme -> updateAdaptiveTheme(event)
            is SettingsEvent.Back -> launch {  navigation.back() }
            is SettingsEvent.DailyGoal.Up -> increaseDailyGoal()
            is SettingsEvent.DailyGoal.Down -> decreaseDailyGoal()
        }
    }

    init {
        launch {
            val config = getAppConfigUseCase()
            _state.value = SettingsState.Content(
                selectedTheme = config.selectedTheme,
                dailyGoal = config.dailyGoal,
                listOfThemes = mutableListOf(
                    ThemeItem(
                        theme = Theme.LIGHT,
                        titleId = Res.string.themeLight
                    ),
                    ThemeItem(
                        theme = Theme.DARK,
                        titleId = Res.string.themeDark
                    ),
                    ThemeItem(
                        theme = Theme.SYSTEM,
                        titleId = Res.string.themeSystem
                    ),
                )
                    .apply {
                        if (isAdaptiveThemeAvailable) {
                            add(
                                ThemeItem(
                                    theme = Theme.ADAPTIVE,
                                    titleId = Res.string.themeAdaptive
                                )
                            )
                        }
                    }
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

    private fun increaseDailyGoal() {
        _state.value = _state.value.copyContent { copy(dailyGoal = dailyGoal + 1) }
        updateConfig()
    }

    private fun decreaseDailyGoal() {
        _state.value = _state.value.copyContent { copy(dailyGoal = dailyGoal - 1) }
        updateConfig()
    }

    private fun updateConfig() {
        (_state.value as? SettingsState.Content)?.let {
            launch {
                saveAppConfigUseCase(
                    AppConfig(
                        selectedTheme = it.selectedTheme,
                        dailyGoal = it.dailyGoal
                    )
                )
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