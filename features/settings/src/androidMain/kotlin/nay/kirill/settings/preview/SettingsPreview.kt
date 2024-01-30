package nay.kirill.settings.preview

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nay.kirill.glassOfWater.ui.theme.GlassOfWaterTheme
import nay.kirill.settings.Settings
import nay.kirill.settings.SettingsState

internal class SettingsStateProvider : PreviewParameterProvider<SettingsState> {

    override val values: Sequence<SettingsState> = sequenceOf(
        SettingsState.Loading,
        SettingsState.Content(isAdaptiveBoolean = false)
    )

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
private fun SettingsScreenPreview(
    @PreviewParameter(SettingsStateProvider::class) state: SettingsState
) {
    GlassOfWaterTheme(isAdaptiveTheme = true) {
        Scaffold {
            Settings(
                state = state
            ) {

            }
        }
    }
}