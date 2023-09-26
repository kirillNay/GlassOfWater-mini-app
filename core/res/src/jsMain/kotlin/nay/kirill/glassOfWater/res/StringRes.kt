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

private val _minus = lastId++
actual val Res.string.minus: Int get() = _minus

private val _plus = lastId++
actual val Res.string.plus: Int get() = _plus