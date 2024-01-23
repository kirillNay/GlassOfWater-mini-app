import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.kirillNay.telegram.miniapp.webApp.webApp
import nay.kirill.glassOfWater.counter.GlassOfWaterScreen
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.glassOfWater.res.buildDimenResources
import nay.kirill.glassOfWater.res.buildStingsResources
import nay.kirill.glassOfWater.res.dimenLocal
import nay.kirill.glassOfWater.res.strsLocal
import org.koin.core.context.GlobalContext

@Composable
internal fun MainScreen() {
    CompositionLocalProvider(
        strsLocal provides buildStingsResources(
            languageCode = webApp.initDataUnsafe.user?.languageCode
        ),
        dimenLocal provides buildDimenResources()
    ) {
        Scaffold {
            Navigator(GlassOfWaterScreen()) { navigator ->
                LaunchedEffect(true) {
                    GlobalContext.get().get<Navigation>().eventsStack.collect { event ->
                        when (event) {
                            is Navigation.Event.Forward -> navigator.push(ScreenRegistry.get(event.screen))
                            is Navigation.Event.Back -> navigator.pop()
                            else -> Unit
                        }
                    }
                }

                CurrentScreen()
            }
        }
    }
}