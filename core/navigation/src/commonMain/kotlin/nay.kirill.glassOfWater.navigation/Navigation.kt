package nay.kirill.glassOfWater.navigation

import kotlinx.coroutines.flow.MutableSharedFlow

class Navigation {

    val eventsStack = MutableSharedFlow<Event>()

    suspend fun navigateTo(screen: SharedScreens) {
        eventsStack.emit(Event.Forward(screen))
    }

    suspend fun back() {
        eventsStack.emit(Event.Back)
    }

    sealed interface Event {

        data class Forward(val screen: SharedScreens) : Event

        data object Back : Event

    }

}