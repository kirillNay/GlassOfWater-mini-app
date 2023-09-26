package nay.kirill.glassOfWater.res

import androidx.compose.runtime.Composable

@Composable
expect fun stringResource(id: Int): String

expect val Res.string.appName: Int

expect val Res.string.minus: Int

expect val Res.string.plus: Int