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

private val _mainTitle = lastId++
actual val Res.string.mainTitle: Int get() = _mainTitle

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

private val _applicationSettings = lastId++
actual val Res.string.applicationSettings: Int get() = _applicationSettings

private val _themeDark = lastId++
actual val Res.string.themeDark: Int get() = _themeDark

private val _themeLight = lastId++
actual val Res.string.themeLight: Int get() = _themeLight

private val _adaptiveTheme = lastId++
actual val Res.string.themeAdaptive: Int get() = _adaptiveTheme

private val _systemTheme = lastId++
actual val Res.string.themeSystem: Int get() = _systemTheme

private val _monday = lastId++
actual val Res.string.monday: Int get() = _monday

private val _tuesday = lastId++
actual val Res.string.tuesday: Int get() = _tuesday

private val _wednesday = lastId++
actual val Res.string.wednesday: Int get() = _wednesday

private val _thursday = lastId++
actual val Res.string.thursday: Int get() = _thursday

private val _friday = lastId++
actual val Res.string.friday: Int get() = _friday

private val _saturday = lastId++
actual val Res.string.saturday: Int get() = _saturday

private val _sunday = lastId++
actual val Res.string.sunday: Int get() = _sunday
