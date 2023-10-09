package nay.kirill.glassOfWater.res

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import nay.kirill.glassOfWater.res.ui.myiconpack.StatsIconLight
import org.jetbrains.skiko.currentNanoTime

@Composable
actual fun painterResource(id: Int): Painter {
    return when(id) {
        Res.drawable.statsIcon -> rememberVectorPainter(StatsIconLight)
        else -> error("Unknown id")
    }
}

private var lastId = currentNanoTime().toInt()


private val _statsIcon = lastId++
actual val Res.drawable.statsIcon: Int get() = _statsIcon