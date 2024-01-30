package nay.kirill.glassOfWater

import MainScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import nay.kirill.glassOfWater.ui.theme.GlassOfWaterTheme
import nay.kirill.healthcare.domain.useCases.ObserveAppConfigUseCase
import org.koin.core.context.GlobalContext

class GlassOfWaterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var isAdaptiveTheme by remember { mutableStateOf(false) }

            LaunchedEffect(true) {
                GlobalContext.get().get<ObserveAppConfigUseCase>().invoke().collect { config ->
                    isAdaptiveTheme = config.isAdaptiveTheme
                }
            }

            GlassOfWaterTheme(
                isAdaptiveTheme = isAdaptiveTheme
            ) {
                MainScreen()
            }
        }
    }

}