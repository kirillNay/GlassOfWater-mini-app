package nay.kirill.glassOfWater.stat

sealed interface WaterStatisticsState {

    data object Loading : WaterStatisticsState

    data class Content(
        val statItems: List<StatItem>,
        val maxIndex: Int,
        val midIndex: Int,
        val minIndex: Int,
        val isNextWeekAvailable: Boolean,
        val isPrevWeekAvailable: Boolean,
        val weekNumber: Int,
        val weekText: String
    ) : WaterStatisticsState

    data object Empty : WaterStatisticsState

    data class StatItem(
        val dayOfWeek: Int,
        val count: Int,
        val isAccomplished: Boolean
    )

}