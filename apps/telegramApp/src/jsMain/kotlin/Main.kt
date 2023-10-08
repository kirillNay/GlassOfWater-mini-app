import androidx.compose.runtime.CompositionLocalProvider
import com.kirillNay.telegram.miniapp.compose.telegramWebApp
import com.kirillNay.telegram.miniapp.webApp.webApp
import di.appModule
import nay.kirill.glassOfWater.GlassOfWaterScreen
import nay.kirill.glassOfWater.res.buildStingsResources
import nay.kirill.glassOfWater.res.strsLocal
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin

fun main() {
    startDi()

    telegramWebApp(
        colorsConverter = GlassOfWaterConverter()
    ) {
        CompositionLocalProvider(
            strsLocal provides buildStingsResources(
                languageCode = webApp.initDataUnsafe.user?.languageCode
            ),
        ) {
            GlassOfWaterScreen(
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
