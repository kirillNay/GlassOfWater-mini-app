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
fun GlassOfWaterTheme(
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
        typography = Typography(
            h1 = TextStyle(
                color = colors.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            ),
            body1 = TextStyle(
                color = colors.onBackground,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            ),
            body2 = TextStyle(
                color = colors.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        )
    ) {
        content()
    }
}