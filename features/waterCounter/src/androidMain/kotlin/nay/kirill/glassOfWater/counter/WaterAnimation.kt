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
actual fun WaterAnimation(
    modifier: Modifier,
    progress: Float
) {
    val color = if (progress == 1F) {
        MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.primary
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.JsonString(
            LottieAnimationSource.createJson(
                r = color.red.toString(),
                g = color.green.toString(),
                b = color.blue.toString()
            )
        )
    )
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress },
    )
}