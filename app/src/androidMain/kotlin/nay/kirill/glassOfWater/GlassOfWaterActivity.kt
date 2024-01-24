package nay.kirill.glassOfWater

import MainScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import nay.kirill.glassOfWater.theme.GlassOfWaterTheme

class GlassOfWaterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GlassOfWaterTheme {
                MainScreen()
            }
        }
    }

}