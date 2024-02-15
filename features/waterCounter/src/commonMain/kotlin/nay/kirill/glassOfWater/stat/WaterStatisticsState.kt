package nay.kirill.glassOfWater.stat

import nay.kirill.healthcare.domain.HealthParams

sealed interface WaterStatisticsState {

    data object Loading : WaterStatisticsState

    data class Content(
        val statItems: List<StatItem>,
        val maxIndex: Int,
        val midIndex: Int,
        val minIndex: Int
    ) : WaterStatisticsState

    data object Empty : WaterStatisticsState

    data class StatItem(
        val date: String,
        val count: Int,
        val isAccomplished: Boolean
    )

}