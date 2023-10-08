package nay.kirill.glassOfWater.counter

sealed interface GlassOfWaterState {

    data class Content(
        val count: Int,
        val isDecreaseEnabled: Boolean = count > 0
    ) : GlassOfWaterState

    data object Loading : GlassOfWaterState

    fun copyContent(block: Content.() -> Content): GlassOfWaterState = when (this) {
        is Content -> block()
        else -> this
    }

}