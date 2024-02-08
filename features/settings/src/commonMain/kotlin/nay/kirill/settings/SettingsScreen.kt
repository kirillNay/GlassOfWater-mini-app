package nay.kirill.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import nay.kirill.glassOfWater.navigation.SharedScreens
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.dimenRes
import nay.kirill.glassOfWater.res.horizontalPadding
import nay.kirill.glassOfWater.res.interfaceSettings
import nay.kirill.glassOfWater.res.settings
import nay.kirill.glassOfWater.res.stringResource
import nay.kirill.glassOfWater.res.themeDark
import nay.kirill.glassOfWater.res.themeLight
import nay.kirill.glassOfWater.res.themeSystem
import nay.kirill.glassOfWater.res.verticalPadding
import nay.kirill.glassOfWater.ui.ErrorState
import nay.kirill.glassOfWater.ui.StatusBar
import nay.kirill.healthcare.domain.Theme

val settingsScreenModule = screenModule {
    register<SharedScreens.Settings> {
        SettingsScreen()
    }
}

class SettingsScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: SettingsViewModel = getScreenModel()
        val state by viewModel.state.collectAsState()

        Settings(
            state = state,
            accept = viewModel::accept
        )
    }

}

@Composable
internal fun Settings(
    state: SettingsState,
    accept: (SettingsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = dimenRes(Res.dimens.horizontalPadding),
                vertical = dimenRes(Res.dimens.verticalPadding)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatusBar(
            text = stringResource(Res.string.settings),
            modifier = Modifier.padding(bottom = 36.dp),
            backAction = { accept(SettingsEvent.Back) }
        )

        when (state) {
            is SettingsState.Content -> Content(
                state = state,
                accept = accept,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
            )

            is SettingsState.Error -> ErrorState(
                modifier = Modifier.padding(top = 46.dp)
            )

            else -> Unit
        }
    }
}

@Composable
private fun Content(
    state: SettingsState.Content,
    accept: (SettingsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(Res.string.interfaceSettings),
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                RadioButtonIcon(
                    stringResource(Res.string.themeLight),
                    state.selectedTheme == Theme.LIGHT,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                ) {
                    accept(SettingsEvent.SetTheme(Theme.LIGHT))
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(start = 36.dp, end = 36.dp)
                )
                RadioButtonIcon(
                    stringResource(Res.string.themeDark),
                    state.selectedTheme == Theme.DARK,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                ) {
                    accept(SettingsEvent.SetTheme(Theme.DARK))
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(start = 36.dp, end = 36.dp)
                )
                RadioButtonIcon(
                    stringResource(Res.string.themeSystem),
                    state.selectedTheme == Theme.SYSTEM,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                ) {
                    accept(SettingsEvent.SetTheme(Theme.SYSTEM))
                }
            }
        }
    }
}

@Composable
private fun RadioButtonIcon(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.CenterVertically),
            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
