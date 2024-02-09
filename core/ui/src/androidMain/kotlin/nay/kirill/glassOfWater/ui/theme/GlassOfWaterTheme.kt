package nay.kirill.glassOfWater.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
actual fun GlassOfWaterTheme(
    theme: UiTheme,
    content: @Composable () -> Unit
) {
    val isSystemDarkTheme = isSystemInDarkTheme()

    val colors = remember(isSystemDarkTheme, theme) {
        when {
            theme == UiTheme.LIGHT -> appLightColors()
            theme == UiTheme.DARK -> appDarkColors()
            theme == UiTheme.SYSTEM && isSystemDarkTheme -> appDarkColors()
            else -> appLightColors()
        }
    }

    MaterialTheme(
        colors = colors,
        typography = appTypography(colors)
    ) {
        content()
    }
}