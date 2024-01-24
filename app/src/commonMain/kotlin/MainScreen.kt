import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import nay.kirill.glassOfWater.counter.GlassOfWaterScreen
import nay.kirill.glassOfWater.navigation.Navigation
import org.koin.core.context.GlobalContext.get

@Composable
internal fun MainScreen() {
    Navigator(GlassOfWaterScreen()) { navigator ->
        LaunchedEffect(true) {
            get().get<Navigation>().eventsStack.collect { event ->
                when (event) {
                    is Navigation.Event.Forward -> navigator.push(ScreenRegistry.get(event.screen))
                    is Navigation.Event.Back -> navigator.pop()
                    else -> Unit
                }
            }
        }

        Scaffold {
            CurrentScreen()
        }
    }
}