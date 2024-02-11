package nay.kirill.glassOfWater.counter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import nay.kirill.glassOfWater.counter.ui.LottieAnimationSource
import org.jetbrains.skia.Rect
import org.jetbrains.skia.skottie.Animation
import org.jetbrains.skia.sksg.InvalidationController

@Composable
actual fun WaterAnimation(
    modifier: Modifier,
    progress: Float // 0 to 1
) {
    val json = LottieAnimationSource.createJson(
        r = MaterialTheme.colors.primary.red.toString(),
        g = MaterialTheme.colors.primary.green.toString(),
        b = MaterialTheme.colors.primary.blue.toString()
    )
    val animation = Animation.makeFromString(json)
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
