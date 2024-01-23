import androidx.compose.material.Scaffold
import androidx.compose.material.Typography
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.kirillNay.telegram.miniapp.compose.telegramWebApp
import com.kirillNay.telegram.miniapp.webApp.webApp
import di.appModule
import nay.kirill.glassOfWater.counter.GlassOfWaterScreen
import nay.kirill.glassOfWater.counter.counterScreenModule
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.glassOfWater.res.buildDimenResources
import nay.kirill.glassOfWater.res.buildStingsResources
import nay.kirill.glassOfWater.res.dimenLocal
import nay.kirill.glassOfWater.res.strsLocal
import nay.kirill.glassOfWater.stat.statsScreenModule
import nay.kirill.settings.settingsScreenModule
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin

fun main() {
    startDi()
    setupNavigation()

    telegramWebApp(
        telegramThemeHandler = get().get<GlassOfWaterThemeHandler>(),
        typography = Typography(
            subtitle1 = TextStyle(
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                letterSpacing = 0.15.sp,
                color = Color.Gray
            ),
            subtitle2 = TextStyle(
                fontWeight = FontWeight.ExtraLight,
                fontSize = 10.sp,
                letterSpacing = 0.15.sp,
                color = Color.Gray
            )
        ),
        animationDuration = 500
    ) {
        CompositionLocalProvider(
            strsLocal provides buildStingsResources(
                languageCode = webApp.initDataUnsafe.user?.languageCode
            ),
            dimenLocal provides buildDimenResources()
        ) {
            Scaffold {
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

                    CurrentScreen()
                }
            }
        }
    }
}

private fun startDi() {
    startKoin {
        modules(appModule)
    }
}

private fun setupNavigation() {
    ScreenRegistry {
        settingsScreenModule()
        counterScreenModule()
        statsScreenModule()
    }
}