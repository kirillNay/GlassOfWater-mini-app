package nay.kirill.glassOfWater.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import nay.kirill.glassOfWater.ui.theme.appDarkColors
import nay.kirill.glassOfWater.ui.theme.appLightColors
import nay.kirill.healthcare.domain.useCases.ObserveAppConfigUseCase
import org.koin.core.context.GlobalContext

@Composable
fun GlassOfWaterTheme(
    content: @Composable () -> Unit
) {
    var colors by remember { mutableStateOf(appLightColors()) }

    val isSystemDarkTheme = isSystemInDarkTheme()

    LaunchedEffect(true) {
        GlobalContext.get().get<ObserveAppConfigUseCase>().invoke().collect { config ->
            colors = when {
                config.isAdaptiveTheme && isSystemDarkTheme -> appDarkColors()
                else -> appLightColors()
            }
        }
    }

    MaterialTheme(colors = colors) {
        content()
    }
}