package nay.kirill.glassOfWater.res

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import org.jetbrains.skiko.currentNanoTime

@Composable
actual fun stringResource(id: Int): String = requireNotNull(strsLocal.current[id])  {
    "String with id $id was not found"
}

val strsLocal = compositionLocalOf { emptyMap<Int, String>() }

private var lastId = currentNanoTime().toInt()

private val _appName = lastId++
actual val Res.string.appName: Int get() = _appName

private val _settings = lastId++
actual val Res.string.settings: Int get() = _settings

private val _stats = lastId++
actual val Res.string.stats: Int get() = _stats

private val _minus = lastId++
actual val Res.string.minus: Int get() = _minus

private val _plus = lastId++
actual val Res.string.plus: Int get() = _plus

private val _noStats = lastId++
actual val Res.string.noStats: Int get() = _noStats

private val _commonError = lastId++
actual val Res.string.commonError: Int get() = _commonError

private val _interfaceSettings = lastId++
actual val Res.string.interfaceSettings: Int get() = _interfaceSettings

private val _themeDark = lastId++
actual val Res.string.themeDark: Int get() = _themeDark

private val _themeLight = lastId++
actual val Res.string.themeLight: Int get() = _themeLight

private val _adaptiveTheme = lastId++
actual val Res.string.themeAdaptive: Int get() = _adaptiveTheme

private val _systemTheme = lastId++
actual val Res.string.themeSystem: Int get() = _systemTheme
