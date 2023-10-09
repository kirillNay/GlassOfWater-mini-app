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

private val _clearDataConfirmation = lastId++
actual val Res.string.clearDataConfirmation: Int get() = _clearDataConfirmation

private val _mockDataConfirmation = lastId++
actual val Res.string.mockDataConfirmation: Int get() = _mockDataConfirmation

private val _clearDataButton = lastId++
actual val Res.string.clearDataButton: Int get() = _clearDataButton

private val _mockDataButton = lastId++
actual val Res.string.mockDataButton: Int get() = _mockDataButton

private val _adaptiveTheme = lastId++
actual val Res.string.adaptiveTheme: Int get() = _adaptiveTheme