package nay.kirill.settings.preview

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.themeDark
import nay.kirill.glassOfWater.res.themeLight
import nay.kirill.glassOfWater.res.themeSystem
import nay.kirill.glassOfWater.ui.theme.GlassOfWaterTheme
import nay.kirill.glassOfWater.ui.theme.UiTheme
import nay.kirill.healthcare.domain.Theme
import nay.kirill.settings.Settings
import nay.kirill.settings.SettingsState
import nay.kirill.settings.ThemeItem

internal class SettingsStateProvider : PreviewParameterProvider<SettingsState> {

    override val values: Sequence<SettingsState> = sequenceOf(
        SettingsState.Loading,
        SettingsState.Content(
            selectedTheme = Theme.SYSTEM,
            dailyGoal = 4,
            listOf(
                ThemeItem(
                    theme = Theme.LIGHT,
                    titleId = Res.string.themeLight
                ),
                ThemeItem(
                    theme = Theme.DARK,
                    titleId = Res.string.themeDark
                ),
                ThemeItem(
                    theme = Theme.SYSTEM,
                    titleId = Res.string.themeSystem
                )
            )
        )
    )

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
private fun SettingsScreenPreview(
    @PreviewParameter(SettingsStateProvider::class) state: SettingsState
) {
    GlassOfWaterTheme(theme = UiTheme.SYSTEM) {
        Scaffold {
            Settings(
                state = state
            ) {

            }
        }
    }
}