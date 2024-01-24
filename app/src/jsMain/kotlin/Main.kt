import androidx.compose.material.Typography
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kirillNay.telegram.miniapp.compose.telegramWebApp
import com.kirillNay.telegram.miniapp.webApp.webApp
import di.appModule
import di.platformModule
import nay.kirill.glassOfWater.res.buildDimenResources
import nay.kirill.glassOfWater.res.buildStingsResources
import nay.kirill.glassOfWater.res.dimenLocal
import nay.kirill.glassOfWater.res.strsLocal
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(
            appModule,
            platformModule
        )
    }
    setupNavigation()

    telegramWebApp(
        telegramThemeHandler = get().get<GlassOfWaterThemeHandler>(),
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
        ),
        animationDuration = 500
    ) {
        CompositionLocalProvider(
            strsLocal provides buildStingsResources(
                languageCode = webApp.initDataUnsafe.user?.languageCode
            ),
            dimenLocal provides buildDimenResources()
        ) {
            MainScreen()
        }
    }
}
