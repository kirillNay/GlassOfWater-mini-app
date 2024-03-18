package nay.kirill.glassOfWater.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import nay.kirill.glassOfWater.counter.WaterCounterScreen
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.glassOfWater.ui.theme.GlassOfWaterTheme
import nay.kirill.glassOfWater.ui.theme.UiTheme
import nay.kirill.healthcare.domain.useCases.ObserveAppConfigUseCase
import org.koin.core.context.GlobalContext.get
import nay.kirill.glassOfWater.main.utils.toUi

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier
) {
    var theme by remember { mutableStateOf(UiTheme.SYSTEM) }

    LaunchedEffect(true) {
        get().get<ObserveAppConfigUseCase>().invoke().collect { config ->
            theme = config.selectedTheme.toUi()
        }
    }

    GlassOfWaterTheme(theme = theme) {
        Navigator(WaterCounterScreen()) { navigator ->
            LaunchedEffect(true) {
                get().get<Navigation>().eventsStack.collect { event ->
                    when (event) {
                        is Navigation.Event.Forward -> navigator.push(ScreenRegistry.get(event.screen))
                        is Navigation.Event.Back -> navigator.pop()
                        else -> Unit
                    }
                }
            }

            Scaffold(
                modifier = modifier
            ) {
                CurrentScreen()
            }
        }
    }
}