package nay.kirill.glassOfWater.counter

import kotlin.math.min

sealed interface WaterCounterState {

    data class Content(
        val count: Int,
        val dailyGoal: Int
    ) : WaterCounterState {

        val progress = min(1F, count.toFloat() / dailyGoal)

    }

    data object Loading : WaterCounterState

    data object Error : WaterCounterState

    fun copyContent(block: Content.() -> Content): WaterCounterState = when (this) {
        is Content -> block()
        else -> this
    }

}