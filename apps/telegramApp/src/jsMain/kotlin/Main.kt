import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import nay.kirill.glassOfWater.GlassOfWaterScreen
import nay.kirill.glassOfWater.res.buildStingsResources
import nay.kirill.glassOfWater.res.strsLocal
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow("GlassOfWater") {
            CompositionLocalProvider(
                strsLocal provides buildStingsResources()
            ) {
                GlassOfWaterScreen()
            }
        }
    }
}
