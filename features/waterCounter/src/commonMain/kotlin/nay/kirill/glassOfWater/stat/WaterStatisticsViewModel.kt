package nay.kirill.glassOfWater.stat

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.kirillNay.telegram.miniapp.webApp.webApp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.healthcare.domain.useCases.GetAllParamsUseCase
import kotlin.coroutines.CoroutineContext

class WaterStatisticsViewModel(
    private val getAllParamsUseCase: GetAllParamsUseCase,
    private val navigation: Navigation
) : ScreenModel {

    private val _state = MutableStateFlow<WaterStatisticsState>(WaterStatisticsState.Loading)
    val state: StateFlow<WaterStatisticsState> = _state

    init {
        webApp.backButton
            .onClick {
                screenModelScope.launch { navigation.back() }
            }
            .show()

        launch {
            val params = getAllParamsUseCase()
                .sortedBy { it.date }
                .takeLast(5)
                .map { it.copy(date = it.date.drop(5).replace('-', '/')) }
            if (params.isEmpty()) {
                _state.value = WaterStatisticsState.Empty
            } else {
                _state.value = WaterStatisticsState.Content(params)
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

    private fun launch(block: suspend () -> Unit) {
        screenModelScope.launch(context = CoroutineExceptionHandler(::onError)) {
            block()
        }
    }

    private fun onError(context: CoroutineContext, error: Throwable) {
        _state.value = WaterStatisticsState.Empty
    }
}