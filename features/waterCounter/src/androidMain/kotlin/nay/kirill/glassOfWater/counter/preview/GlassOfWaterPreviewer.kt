package nay.kirill.glassOfWater.counter.preview

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nay.kirill.glassOfWater.counter.GlassOfWater
import nay.kirill.glassOfWater.counter.GlassOfWaterState
import nay.kirill.glassOfWater.ui.theme.appLightColors

internal class GlassOfWaterStateProvider : PreviewParameterProvider<GlassOfWaterState> {

    override val values: Sequence<GlassOfWaterState> = sequenceOf(
        GlassOfWaterState.Loading,
        GlassOfWaterState.Error,
        GlassOfWaterState.Content(4)
    )

}

@Preview
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
private fun GlassOfWaterPreview(
        @PreviewParameter(GlassOfWaterStateProvider::class) state: GlassOfWaterState
) {
    MaterialTheme (
        colors = appLightColors()
    ){
        Scaffold {
            GlassOfWater(
                state = state,
                accept = {}
            )
        }
    }
}