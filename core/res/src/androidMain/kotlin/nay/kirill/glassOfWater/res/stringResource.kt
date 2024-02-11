package nay.kirill.glassOfWater.res

import androidx.compose.runtime.Composable

@Composable
actual fun stringResource(id: Int): String = androidx.compose.ui.res.stringResource(id = id)

actual val Res.string.appName: Int get() = R.string.app_name

actual val Res.string.settings: Int get() = R.string.settings

actual val Res.string.stats: Int get() = R.string.stats

actual val Res.string.minus: Int get() = R.string.minus

actual val Res.string.plus: Int get() = R.string.plus

actual val Res.string.noStats: Int get() = R.string.noStats

actual val Res.string.commonError: Int get() = R.string.commonError

actual val Res.string.interfaceSettings: Int get() = R.string.interfaceSettings

actual val Res.string.themeLight: Int get() = R.string.themeLight

actual val Res.string.themeDark: Int get() = R.string.themeDark

actual val Res.string.themeSystem: Int get() = R.string.themeSystem

actual val Res.string.themeAdaptive: Int get() = R.string.themeAdaptive

actual val Res.string.applicationSettings: Int get() = R.string.applicationSettings

actual val Res.string.mainTitle: Int get() = R.string.mainTitle