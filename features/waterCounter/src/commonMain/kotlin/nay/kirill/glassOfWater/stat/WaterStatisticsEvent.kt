package nay.kirill.glassOfWater.stat

sealed interface WaterStatisticsEvent {

    data object Back : WaterStatisticsEvent

}