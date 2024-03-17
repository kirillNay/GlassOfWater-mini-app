package nay.kirill.glassOfWater.main

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.kirillNay.telegram.miniapp.compose.telegramWebApp
import com.kirillNay.telegram.miniapp.webApp.webApp
import nay.kirill.glassOfWater.main.di.appModule
import nay.kirill.glassOfWater.main.di.platformModule
import nay.kirill.glassOfWater.res.buildDimenResources
import nay.kirill.glassOfWater.res.buildStingsResources
import nay.kirill.glassOfWater.res.dimenLocal
import nay.kirill.glassOfWater.res.strsLocal
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(
            appModule,
            platformModule
        )
    }
    setupNavigation()

    telegramWebApp { style ->
        CompositionLocalProvider(
            strsLocal provides buildStingsResources(
                languageCode = webApp.initDataUnsafe.user?.languageCode
            ),
            dimenLocal provides buildDimenResources()
        ) {
            val height = remember(style.viewPort.viewPortHeight) {
                max(style.viewPort.viewPortHeight, 500.dp)
            }
            MainScreen(
                modifier = Modifier.height(height)
            )
        }
    }
}
