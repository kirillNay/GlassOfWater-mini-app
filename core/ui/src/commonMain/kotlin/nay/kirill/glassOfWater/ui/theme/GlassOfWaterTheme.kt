package nay.kirill.glassOfWater.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun GlassOfWaterTheme(
    isAdaptiveTheme: Boolean,
    content: @Composable () -> Unit
) {
    val isSystemDarkTheme = isSystemInDarkTheme()

    val colors = remember(isSystemDarkTheme, isAdaptiveTheme) {
        when {
            isAdaptiveTheme && isSystemDarkTheme -> appDarkColors()
            else -> appLightColors()
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography(
            h1 = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    ) {
        content()
    }
}