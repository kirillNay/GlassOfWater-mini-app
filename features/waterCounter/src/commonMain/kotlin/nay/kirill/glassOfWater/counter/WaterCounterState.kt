package nay.kirill.glassOfWater.counter

sealed interface WaterCounterState {

    data class Content(
        val count: Int
    ) : WaterCounterState

    data object Loading : WaterCounterState

    data object Error : WaterCounterState

    fun copyContent(block: Content.() -> Content): WaterCounterState = when (this) {
        is Content -> block()
        else -> this
    }

}