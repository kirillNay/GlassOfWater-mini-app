package nay.kirill.glassOfWater.counter

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nay.kirill.healthcare.domain.useCases.GetTodayParamsUseCase
import nay.kirill.healthcare.domain.useCases.UpdateTodayWaterUseCase
import nay.kirill.kmpArch.navigation.NavigationStack
import nay.kirill.kmpArch.ViewModel
import nay.kirill.kmpArch.navigation.Screen
import kotlin.coroutines.CoroutineContext

class GlassOfWaterViewModel(
    private val getTodayParamsUseCase: GetTodayParamsUseCase,
    private val updateTodayWaterUseCase: UpdateTodayWaterUseCase,
    private val navigationStack: NavigationStack
): ViewModel() {

    private val _state = MutableStateFlow<GlassOfWaterState>(GlassOfWaterState.Loading)
    val state: StateFlow<GlassOfWaterState> = _state.apply { onEach { console.log(it.toString()) } }

    init {
        launch {
            val params = getTodayParamsUseCase()
            _state.value = GlassOfWaterState.Content(params.waterCount)
        }
    }

    override fun onError(context: CoroutineContext, error: Throwable) {
        _state.value = GlassOfWaterState.Error
    }

    fun increaseCount() {
        _state.value = state.value.copyContent { copy(count = count + 1) }
        updateCount()
    }

    fun decreaseCount() {
        _state.value = state.value.copyContent { copy(count = count - 1) }
        updateCount()
    }

    fun navigateToStats() {
        navigationStack.push(Screen.STATS.route)
    }

    fun navigateToSettings() {
        navigationStack.push(Screen.SETTINGS.route)
    }

    private fun updateCount() {
        (_state.value as? GlassOfWaterState.Content)?.count?.let {
            launch { updateTodayWaterUseCase(it) }
        }
    }

}