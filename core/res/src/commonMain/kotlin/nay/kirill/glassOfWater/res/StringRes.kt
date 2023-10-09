package nay.kirill.glassOfWater.res

import androidx.compose.runtime.Composable

@Composable
expect fun stringResource(id: Int): String

expect val Res.string.appName: Int

expect val Res.string.settings: Int

expect val Res.string.stats: Int

expect val Res.string.minus: Int

expect val Res.string.plus: Int

expect val Res.string.noStats: Int

expect val Res.string.clearDataConfirmation: Int

expect val Res.string.mockDataConfirmation: Int

expect val Res.string.clearDataButton: Int

expect val Res.string.mockDataButton: Int

expect val Res.string.adaptiveTheme: Int