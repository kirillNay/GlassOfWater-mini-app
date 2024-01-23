package nay.kirill.glassOfWater.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface SharedScreens : ScreenProvider {

    data object SettingsScreen : SharedScreens

    data object StatsScreen : SharedScreens

    data object CounterScreen : SharedScreens

}