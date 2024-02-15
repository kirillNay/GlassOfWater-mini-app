package nay.kirill.glassOfWater.navigation

import kotlinx.coroutines.flow.MutableSharedFlow

class Navigation {

    val eventsStack = MutableSharedFlow<Event>(
        extraBufferCapacity = 1
    )

    fun navigateTo(screen: SharedScreens) {
        eventsStack.tryEmit(Event.Forward(screen))
    }

    fun back() {
        eventsStack.tryEmit(Event.Back)
    }

    sealed interface Event {

        data class Forward(val screen: SharedScreens) : Event

        data object Back : Event

    }

}