package nay.kirill.glassOfWater

import MainScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import nay.kirill.healthcare.domain.useCases.ObserveAppConfigUseCase
import org.koin.core.context.GlobalContext.get

class GlassOfWaterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var colors by remember { mutableStateOf(lightColors()) }

            val isSystemDarkTheme = isSystemInDarkTheme()

            LaunchedEffect(true) {
                get().get<ObserveAppConfigUseCase>().invoke().collect { config ->
                    colors = when {
                        config.isAdaptiveTheme && isSystemDarkTheme -> darkColors()
                        else -> lightColors()
                    }
                }
            }

            MaterialTheme(
                colors = colors
            ) {
                MainScreen()
            }
        }
    }

}