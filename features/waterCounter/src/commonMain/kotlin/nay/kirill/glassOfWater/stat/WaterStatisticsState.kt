package nay.kirill.glassOfWater.stat

import nay.kirill.healthcare.domain.HealthParams

sealed interface WaterStatisticsState {

    data object Loading : WaterStatisticsState

    data class Content(
        val paramsList: List<HealthParams>
    ) : WaterStatisticsState

    data object Empty : WaterStatisticsState

}