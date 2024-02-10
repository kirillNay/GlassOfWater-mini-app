package nay.kirill.glassOfWater.counter.preview

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nay.kirill.glassOfWater.counter.WaterCounter
import nay.kirill.glassOfWater.counter.WaterCounterState
import nay.kirill.glassOfWater.ui.theme.appLightColors

internal class GlassOfWaterStateProvider : PreviewParameterProvider<WaterCounterState> {

    override val values: Sequence<WaterCounterState> = sequenceOf(
        WaterCounterState.Loading,
        WaterCounterState.Error,
        WaterCounterState.Content(4)
    )

}

@Preview
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
private fun GlassOfWaterPreview(
        @PreviewParameter(GlassOfWaterStateProvider::class) state: WaterCounterState
) {
    MaterialTheme (
        colors = appLightColors()
    ){
        Scaffold {
            WaterCounter(
                state = state,
                accept = {}
            )
        }
    }
}