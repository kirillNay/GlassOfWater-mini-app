package nay.kirill.glassOfWater.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import com.kirillNay.telegram.miniapp.compose.TelegramColors
import com.kirillNay.telegram.miniapp.webApp.ColorScheme
import com.kirillNay.telegram.miniapp.webApp.EventType
import com.kirillNay.telegram.miniapp.webApp.webApp

@OptIn(ExperimentalStdlibApi::class)
@Composable
actual fun GlassOfWaterTheme(
    theme: UiTheme,
    content: @Composable () -> Unit
) {
    var telegramColors by remember { mutableStateOf(TelegramColors.fromWebApp()) }

    LaunchedEffect(true) {
        webApp.addEventHandler(EventType.THEME_CHANGED) {
            telegramColors =  TelegramColors.fromWebApp()
        }
    }

    val colors = remember(theme, telegramColors) {
        when {
            theme == UiTheme.ADAPTIVE && webApp.colorScheme == ColorScheme.DARK -> appDarkColors(
                primary = telegramColors.buttonColor,
                onPrimary = telegramColors.buttonTextColor,
                background = telegramColors.backgroundColor,
                secondaryVariant = telegramColors.buttonColor
            )
            theme == UiTheme.ADAPTIVE && webApp.colorScheme == ColorScheme.LIGHT -> appLightColors(
                primary = telegramColors.buttonColor,
                onPrimary = telegramColors.buttonTextColor,
                background = telegramColors.backgroundColor,
                secondaryVariant = telegramColors.buttonColor
            )
            theme == UiTheme.LIGHT -> appLightColors()
            theme == UiTheme.DARK -> appDarkColors()
            theme == UiTheme.SYSTEM && webApp.colorScheme == ColorScheme.DARK -> appDarkColors()
            else -> appLightColors()
        }
    }

    LaunchedEffect(colors.background) {
        webApp.setHeaderColor("#" + (colors.background.toArgb().toHexString(HexFormat.UpperCase)).drop(2))
    }

    MaterialTheme(
        colors = colors,
        typography = appTypography(colors)
    ) {
        content()
    }
}