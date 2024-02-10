package nay.kirill.glassOfWater.counter

sealed interface WaterCounterEvent {

    data object IncreaseCount : WaterCounterEvent

    data object DecreaseCount : WaterCounterEvent

    data object OpenStats : WaterCounterEvent

    data object OpenSettings : WaterCounterEvent

}