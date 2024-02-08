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
import nay.kirill.glassOfWater.ui.theme.UiTheme
import nay.kirill.healthcare.domain.Theme
import nay.kirill.healthcare.domain.useCases.ObserveAppConfigUseCase
import org.koin.core.context.GlobalContext

class GlassOfWaterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var theme by remember { mutableStateOf(UiTheme.SYSTEM) }

            LaunchedEffect(true) {
                GlobalContext.get().get<ObserveAppConfigUseCase>().invoke().collect { config ->
                    theme = when(config.selectedTheme) {
                        Theme.SYSTEM -> UiTheme.SYSTEM
                        Theme.DARK -> UiTheme.DARK
                        Theme.LIGHT -> UiTheme.LIGHT
                    }
                }
            }

            GlassOfWaterTheme(theme = theme) {
                MainScreen()
            }
        }
    }

}