package nay.kirill.glassOfWater

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource
import kotlin.math.roundToInt
import org.jetbrains.skia.Rect
import org.jetbrains.skia.skottie.Animation
import org.jetbrains.skia.sksg.InvalidationController

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LottieAnimation() {
    val lottieData = resource("water_drink_1.json").readBytes().apply { String(this) }
    val animation = Animation.makeFromString("")
    InfiniteAnimation(animation, Modifier.fillMaxSize())
}

@Composable
fun InfiniteAnimation(animation: Animation, modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = animation.duration,
        animationSpec = infiniteRepeatable(
            animation = tween((animation.duration * 1000).roundToInt(), easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val invalidationController = remember { InvalidationController() }
    animation.seekFrameTime(time, invalidationController)
    Canvas(modifier) {
        drawIntoCanvas {
            animation.render(
                canvas = it.nativeCanvas,
                dst = Rect.makeWH(size.width, size.height)
            )
        }
    }
}