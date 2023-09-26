package nay.kirill.glassOfWater.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import kotlin.math.roundToInt
import org.jetbrains.skia.Rect
import org.jetbrains.skia.skottie.Animation
import org.jetbrains.skia.sksg.InvalidationController

@Composable
actual fun WaterAnimation(
    modifier: Modifier,
    progress: Float // 0 to 1
) {
    val animation = Animation.makeFromString(LottieAnimation.JSON)
    val invalidationController = remember { InvalidationController() }
    val time = animation.duration * progress

    Canvas(modifier.fillMaxSize()) {
        drawIntoCanvas {
            animation
                .seekFrameTime(time, invalidationController)
                .render(
                    canvas = it.nativeCanvas,
                    dst = Rect.makeWH(size.width, size.height)
                )
        }
    }
}

@Composable
private fun InfiniteAnimation(animation: Animation, modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val time by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = animation.duration,
        animationSpec = infiniteRepeatable(
            animation = tween((animation.duration * 1000).roundToInt(), easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )


}