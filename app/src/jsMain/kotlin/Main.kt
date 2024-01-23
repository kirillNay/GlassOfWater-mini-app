import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kirillNay.telegram.miniapp.compose.telegramWebApp
import di.startDi
import org.koin.core.context.GlobalContext.get

fun main() {
    startDi()
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
        MainScreen()
    }
}
