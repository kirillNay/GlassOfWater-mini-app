package nay.kirill.glassOfWater

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
actual fun WaterAnimation(
    modifier: Modifier,
    progress: Float
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(nay.kirill.glassOfWater.ui.LottieAnimation.JSON))

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress }
    )
}