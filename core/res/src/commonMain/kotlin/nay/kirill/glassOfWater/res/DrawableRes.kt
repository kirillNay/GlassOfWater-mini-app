package nay.kirill.glassOfWater.res

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
expect fun painterResource(id: Int): Painter

expect val Res.drawable.statsIcon: Int