import androidx.compose.material.Colors
import androidx.compose.runtime.Stable
import com.kirillNay.telegram.miniapp.compose.theme.ColorsConverter
import com.kirillNay.telegram.miniapp.compose.theme.TelegramColors
import com.kirillNay.telegram.miniapp.webApp.ColorScheme
import nay.kirill.glassOfWater.ui.theme.appDarkColors
import nay.kirill.glassOfWater.ui.theme.appLightColors

@Stable
class GlassOfWaterConverter {

    fun convert(
        themeParams: TelegramColors,
        colorScheme: ColorScheme,
        isAdaptive: Boolean
    ): Colors {
        return when {
            isAdaptive && colorScheme == ColorScheme.LIGHT -> appLightColors(
                primary = themeParams.buttonColor,
                onPrimary = themeParams.buttonTextColor,
                background = themeParams.backgroundColor,
                secondaryVariant = themeParams.buttonColor
            )
            isAdaptive && colorScheme == ColorScheme.DARK -> appDarkColors(
                primary = themeParams.buttonColor,
                onPrimary = themeParams.buttonTextColor,
                background = themeParams.backgroundColor,
                secondaryVariant = themeParams.buttonColor
            )
            colorScheme == ColorScheme.LIGHT -> appLightColors(
                background = themeParams.backgroundColor
            )
            colorScheme == ColorScheme.DARK -> appDarkColors(
                background = themeParams.backgroundColor
            )
            else -> ColorsConverter.Default().convert(themeParams, colorScheme)
        }
    }

}