package nay.kirill.glassOfWater.counter

sealed interface GlassOfWaterState {

    data class Content(
        val count: Int
    ) : GlassOfWaterState

    data object Loading : GlassOfWaterState

    data object Error : GlassOfWaterState

    fun copyContent(block: Content.() -> Content): GlassOfWaterState = when (this) {
        is Content -> block()
        else -> this
    }

}