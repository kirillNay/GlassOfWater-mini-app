import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kirillNay.telegram.miniapp.compose.telegramWebApp
import com.kirillNay.telegram.miniapp.webApp.webApp
import di.appModule
import nay.kirill.glassOfWater.counter.GlassOfWaterScreen
import nay.kirill.glassOfWater.res.buildStingsResources
import nay.kirill.glassOfWater.res.strsLocal
import nay.kirill.glassOfWater.stat.StatisticsScreen
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin

fun main() {
    startDi()

    telegramWebApp(
        colorsConverter = GlassOfWaterConverter(),
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
        )
    ) {
        CompositionLocalProvider(
            strsLocal provides buildStingsResources(
                languageCode = webApp.initDataUnsafe.user?.languageCode
            ),
        ) {
            StatisticsScreen(
                viewModel = get().get()
            )
        }
    }
}

private fun startDi() {
    startKoin {
        modules(appModule)
    }
}
