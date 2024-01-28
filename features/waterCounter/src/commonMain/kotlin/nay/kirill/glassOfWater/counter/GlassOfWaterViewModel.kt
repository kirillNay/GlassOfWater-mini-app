package nay.kirill.glassOfWater.counter

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.glassOfWater.navigation.SharedScreens
import nay.kirill.healthcare.domain.useCases.GetTodayParamsUseCase
import nay.kirill.healthcare.domain.useCases.UpdateTodayWaterUseCase

class GlassOfWaterViewModel(
    private val getTodayParamsUseCase: GetTodayParamsUseCase,
    private val updateTodayWaterUseCase: UpdateTodayWaterUseCase,
    private val navigation: Navigation
) : ScreenModel {

    private val _state = MutableStateFlow<GlassOfWaterState>(GlassOfWaterState.Loading)
    val state: StateFlow<GlassOfWaterState> = _state

    init {
        launch {
            val params = getTodayParamsUseCase()
            _state.value = GlassOfWaterState.Content(params.waterCount)
        }
    }

    fun accept(event: CounterEvent) {
        when(event) {
            is CounterEvent.DecreaseCount -> decreaseCount()
            is CounterEvent.IncreaseCount -> increaseCount()
            is CounterEvent.OpenSettings -> navigateToSettings()
            is CounterEvent.OpenStats -> navigateToStats()
        }
    }

    private fun onError(error: Throwable) {
        _state.value = GlassOfWaterState.Error
    }

    private fun increaseCount() {
        _state.value = state.value.copyContent { copy(count = count + 1) }
        updateCount()
    }

    private fun decreaseCount() {
        _state.value = state.value.copyContent { copy(count = count - 1) }
        updateCount()
    }

    private fun navigateToStats() {
        screenModelScope.launch {
            navigation.navigateTo(SharedScreens.Stats)
        }
    }

    private fun navigateToSettings() {
        screenModelScope.launch {
            navigation.navigateTo(SharedScreens.Settings)
        }
    }

    private fun updateCount() {
        (_state.value as? GlassOfWaterState.Content)?.count?.let {
            launch { updateTodayWaterUseCase(it) }
        }
    }

    private fun launch(block: suspend () -> Unit) {
        screenModelScope.launch(context = CoroutineExceptionHandler {_, error -> onError(error) }) { block() }
    }

}