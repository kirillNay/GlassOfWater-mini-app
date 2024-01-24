package nay.kirill.glassOfWater.counter

sealed interface CounterEvent {

    data object IncreaseCount : CounterEvent

    data object DecreaseCount : CounterEvent

    data object OpenStats : CounterEvent

    data object OpenSettings : CounterEvent

}