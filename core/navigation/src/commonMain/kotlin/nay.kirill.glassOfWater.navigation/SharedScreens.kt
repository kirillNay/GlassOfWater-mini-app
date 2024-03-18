package nay.kirill.glassOfWater.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface SharedScreens : ScreenProvider {

    data object Settings : SharedScreens

    data object Stats : SharedScreens

    data object Counter : SharedScreens

}