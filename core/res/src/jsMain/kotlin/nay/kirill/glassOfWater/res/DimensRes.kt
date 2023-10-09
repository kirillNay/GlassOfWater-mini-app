package nay.kirill.glassOfWater.res

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp

@Composable
actual fun dimenRes(id: Int): Dp = dimenLocal.current[id] ?: error("Unknown dimen id")

val dimenLocal = compositionLocalOf { emptyMap<Int, Dp>() }

actual val Res.dimens.horizontalPadding: Int get() = 0

actual val Res.dimens.verticalPadding: Int get() = 1