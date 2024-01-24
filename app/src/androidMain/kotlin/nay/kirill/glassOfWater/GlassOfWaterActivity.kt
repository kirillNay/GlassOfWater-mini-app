package nay.kirill.glassOfWater

import MainScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import di.startDi
import setupNavigation

class GlassOfWaterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startDi()
        setupNavigation()

        setContent {
            MainScreen()
        }
    }

}