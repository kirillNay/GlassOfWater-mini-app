package nay.kirill.glassOfWater.stat

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.healthcare.domain.useCases.GetAllParamsUseCase
import nay.kirill.healthcare.domain.useCases.GetAppConfigUseCase
import kotlin.coroutines.CoroutineContext

class WaterStatisticsViewModel(
    private val getAllParamsUseCase: GetAllParamsUseCase,
    private val getAppConfigUseCase: GetAppConfigUseCase,
    private val navigation: Navigation
) : ScreenModel {

    private val _state = MutableStateFlow<WaterStatisticsState>(WaterStatisticsState.Loading)
    val state: StateFlow<WaterStatisticsState> = _state

    init {
//        webApp.backButton
//            .onClick {
//                screenModelScope.launch { navigation.back() }
//            }
//            .show()

        launch {
            val params = getAllParamsUseCase().params

            val appConfig = getAppConfigUseCase()

            if (params.isEmpty()) {
                _state.value = WaterStatisticsState.Empty
            } else {
                _state.value = WaterStatisticsState.Content(
                    statItems = params.map {
                        WaterStatisticsState.StatItem(
                            date = "${it.date.monthNumber} / ${it.date.dayOfMonth}",
                            count = it.waterCount,
                            isAccomplished = it.waterCount >= appConfig.dailyGoal
                        )
                    },
                    maxIndex = params.maxOf { it.waterCount },
                    midIndex = params.maxOf { it.waterCount } / 2,
                    minIndex = 0
                )
            }
        }
    }

    fun reduce(event: WaterStatisticsEvent) {
        when (event) {
            is WaterStatisticsEvent.Back -> back()
        }
    }

    private fun back() {
        navigation.back()
    }

    override fun onDispose() {
        super.onDispose()
//        webApp.backButton
//            .offClick {
//                screenModelScope.launch { navigation.back() }
//            }
//            .hide()
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