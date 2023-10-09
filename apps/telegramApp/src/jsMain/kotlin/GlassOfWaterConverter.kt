import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.kirillNay.telegram.miniapp.compose.theme.ColorsConverter
import com.kirillNay.telegram.miniapp.compose.theme.TelegramColors
import com.kirillNay.telegram.miniapp.webApp.ColorScheme

@Stable
class GlassOfWaterConverter {

    fun convert(
        themeParams: TelegramColors,
        colorScheme: ColorScheme,
        isAdaptive: Boolean
    ): Colors {
        return when {
            isAdaptive && colorScheme == ColorScheme.LIGHT -> lightColors(
                primary = themeParams.buttonColor,
                onPrimary = themeParams.buttonTextColor,
                background = themeParams.backgroundColor,
                secondaryVariant = themeParams.buttonColor
            )
            isAdaptive && colorScheme == ColorScheme.DARK -> darkColors(
                primary = themeParams.buttonColor,
                onPrimary = themeParams.buttonTextColor,
                background = themeParams.backgroundColor,
                secondaryVariant = themeParams.buttonColor
            )
            colorScheme == ColorScheme.LIGHT -> lightColors(
                primary = Color(0xFF005FB0),
                onPrimary = Color(0xFFFFFFFF),
                secondaryVariant = Color(0xFF001C3B),
                background = themeParams.backgroundColor
            )
            colorScheme == ColorScheme.DARK -> darkColors(
                primary = Color(0xFFA6C8FF),
                onPrimary = Color(0xFF00305F),
                secondaryVariant = Color(0xFFD5E3FF),
                background = themeParams.backgroundColor
            )
            else -> ColorsConverter.Default().convert(themeParams, colorScheme)
        }
    }

}