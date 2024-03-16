package nay.kirill.glassOfWater.stat

sealed interface WaterStatisticsEvent {

    data object Back : WaterStatisticsEvent

    sealed interface Week : WaterStatisticsEvent {

        data object Increase : Week

        data object Decrease : Week

    }

}