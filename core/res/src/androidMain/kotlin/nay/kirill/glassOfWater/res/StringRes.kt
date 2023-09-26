package nay.kirill.glassOfWater.res

import androidx.compose.runtime.Composable
import nay.kirill.glassOfWater.waterCounter.R

@Composable
actual fun stringResource(id: Int): String =
    androidx.compose.ui.res.stringResource(id)

actual val Res.string.appName: Int get() = R.string.app_name

actual val Res.string.minus: Int get() = R.string.minus

actual val Res.string.plus: Int get() = R.string.plus