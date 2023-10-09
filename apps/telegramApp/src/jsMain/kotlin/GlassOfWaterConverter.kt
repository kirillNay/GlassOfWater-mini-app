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
            isAdaptive -> lightColors(
                primary = themeParams.buttonColor,
                onPrimary = themeParams.buttonTextColor,
            )
            colorScheme == ColorScheme.LIGHT -> lightColors(
                primary = Color(0xFF005FB0),
                onPrimary = Color(0xFFFFFFFF),
            )
            colorScheme == ColorScheme.DARK -> darkColors(
                primary = Color(0xFFA6C8FF),
                onPrimary = Color(0xFF00305F)
            )
            else -> ColorsConverter.Default().convert(themeParams, colorScheme)
        }
    }

}