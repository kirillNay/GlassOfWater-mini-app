package nay.kirill.glassOfWater.counter

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import nay.kirill.glassOfWater.counter.ui.LottieAnimationSource

@Composable
actual fun WaterAnimation(modifier: Modifier, progress: Float) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.JsonString(
            LottieAnimationSource.createJson(
                r = MaterialTheme.colors.primary.red.toString(),
                g = MaterialTheme.colors.primary.green.toString(),
                b = MaterialTheme.colors.primary.blue.toString()
            )
        )
    )
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}