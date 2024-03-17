package nay.kirill.glassOfWater.stat

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.healthcare.domain.useCases.GetAllParamsUseCase
import nay.kirill.healthcare.domain.useCases.GetAppConfigUseCase
import kotlin.coroutines.CoroutineContext

expect class WaterStatisticsViewModel : BaseWaterStatisticsViewModel

abstract class BaseWaterStatisticsViewModel(
    private val getAllParamsUseCase: GetAllParamsUseCase,
    private val getAppConfigUseCase: GetAppConfigUseCase,
    private val navigation: Navigation
) : ScreenModel {

    private val _state = MutableStateFlow<WaterStatisticsState>(WaterStatisticsState.Loading)
    val state: StateFlow<WaterStatisticsState> = _state

    init {
        loadParams(0)
    }

    private fun loadParams(week: Int) {
        launch {
            val page = getAllParamsUseCase(week)
            val params = page.params

            val appConfig = getAppConfigUseCase()

            if (params.isEmpty()) {
                _state.value = WaterStatisticsState.Empty
            } else {
                val maxIndex = params.maxOf { it.waterCount }.takeIf { it > 1 } ?: appConfig.dailyGoal

                _state.value = WaterStatisticsState.Content(
                    statItems = params.map {
                        WaterStatisticsState.StatItem(
                            dayOfWeek = it.date.dayOfWeek.ordinal,
                            count = it.waterCount,
                            isAccomplished = it.waterCount >= appConfig.dailyGoal
                        )
                    },
                    maxIndex = maxIndex,
                    midIndex = maxIndex / 2,
                    minIndex = 0,
                    isNextWeekAvailable = page.weekNumber != 0,
                    isPrevWeekAvailable = page.totalWeeks > page.weekNumber + 1,
                    weekText = params.firstOrNull()?.date?.toPrettyDate().orEmpty() + "    —    " +
                            params.lastOrNull()?.date?.toPrettyDate().orEmpty(),
                    weekNumber = page.weekNumber
                )
            }
        }
    }

    fun reduce(event: WaterStatisticsEvent) {
        when (event) {
            is WaterStatisticsEvent.Back -> back()
            is WaterStatisticsEvent.Week.Increase -> (state.value as? WaterStatisticsState.Content)?.let {
                loadParams(it.weekNumber - 1)
            }
            is WaterStatisticsEvent.Week.Decrease -> (state.value as? WaterStatisticsState.Content)?.let {
                loadParams(it.weekNumber + 1)
            }
        }
    }

    protected fun back() {
        navigation.back()
    }

    private fun launch(block: suspend () -> Unit) {
        screenModelScope.launch(context = CoroutineExceptionHandler(::onError)) {
            block()
        }
    }

    private fun onError(context: CoroutineContext, error: Throwable) {
        _state.value = WaterStatisticsState.Empty
    }

    private fun LocalDate.toPrettyDate() = "${dayOfMonth.toString().padStart(2, '0')}.${monthNumber.toString().padStart(2, '0')}"
}