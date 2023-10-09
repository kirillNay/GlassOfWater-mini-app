import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.Scaffold
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kirillNay.telegram.miniapp.compose.telegramWebApp
import com.kirillNay.telegram.miniapp.webApp.webApp
import di.appModule
import nay.kirill.glassOfWater.counter.GlassOfWaterScreen
import nay.kirill.glassOfWater.res.buildDimenResources
import nay.kirill.glassOfWater.res.buildStingsResources
import nay.kirill.glassOfWater.res.dimenLocal
import nay.kirill.glassOfWater.res.strsLocal
import nay.kirill.glassOfWater.stat.WaterStatisticsScreen
import nay.kirill.kmpArch.navigation.Screen
import nay.kirill.settings.SettingsScreen
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin

fun main() {
    startDi()

    telegramWebApp(
        themeHandler = get().get<GlassOfWaterThemeHandler>(),
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
                val appState = rememberAppState()
                Screen(appState.currentRoute == Screen.COUNTER.route) { GlassOfWaterScreen(remember { get().get() }) }
                Screen(appState.currentRoute == Screen.STATS.route) { WaterStatisticsScreen(remember { get().get() }) }
                Screen(appState.currentRoute == Screen.SETTINGS.route) { SettingsScreen(remember { get().get() }) }
            }
        }
    }
}

@Composable
private fun Screen(
    isVisible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 300, easing = LinearEasing)),
        exit = fadeOut(animationSpec = tween(durationMillis = 150)),
    ) {
        content()
    }
}

private fun startDi() {
    startKoin {
        modules(appModule)
    }
}

@Composable
private fun rememberAppState(): AppState = remember {
    AppState(navigationStack = get().get())
}
