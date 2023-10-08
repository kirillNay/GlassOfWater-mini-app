import androidx.compose.runtime.CompositionLocalProvider
import com.kirillNay.telegram.miniapp.compose.telegramWebApp
import com.kirillNay.telegram.miniapp.webApp.webApp
import nay.kirill.glassOfWater.GlassOfWaterScreen
import nay.kirill.glassOfWater.res.buildStingsResources
import nay.kirill.glassOfWater.res.strsLocal

fun main() {
    telegramWebApp(
        colorsConverter = GlassOfWaterConverter()
    ) {
        CompositionLocalProvider(
            strsLocal provides buildStingsResources(
                languageCode = webApp.initDataUnsafe.user?.languageCode
            ),
        ) {
            GlassOfWaterScreen()
        }
    }
}
