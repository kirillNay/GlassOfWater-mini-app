package nay.kirill.glassOfWater.res

import androidx.compose.runtime.Composable

@Composable
expect fun stringResource(id: Int): String

expect val Res.string.appName: Int

expect val Res.string.mainTitle: Int

expect val Res.string.settings: Int

expect val Res.string.stats: Int

expect val Res.string.minus: Int

expect val Res.string.plus: Int

expect val Res.string.noStats: Int

expect val Res.string.commonError: Int

expect val Res.string.interfaceSettings: Int

expect val Res.string.applicationSettings: Int

expect val Res.string.themeDark: Int

expect val Res.string.themeLight: Int

expect val Res.string.themeSystem: Int

expect val Res.string.themeAdaptive: Int