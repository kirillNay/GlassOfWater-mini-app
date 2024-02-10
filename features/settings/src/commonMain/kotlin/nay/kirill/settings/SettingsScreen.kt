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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import nay.kirill.glassOfWater.res.applicationSettings
import nay.kirill.glassOfWater.res.dimenRes
import nay.kirill.glassOfWater.res.horizontalPadding
import nay.kirill.glassOfWater.res.interfaceSettings
import nay.kirill.glassOfWater.res.minus
import nay.kirill.glassOfWater.res.plus
import nay.kirill.glassOfWater.res.settings
import nay.kirill.glassOfWater.res.stringResource
import nay.kirill.glassOfWater.res.verticalPadding
import nay.kirill.glassOfWater.ui.ErrorState
import nay.kirill.glassOfWater.ui.StatusBar

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
        SettingsItem(
            title = stringResource(Res.string.interfaceSettings)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                state.listOfThemes.forEachIndexed { index, themeItem ->
                    if (index != 0) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .padding(start = 36.dp, end = 36.dp)
                        )
                    }
                    RadioButtonIcon(
                        text = stringResource(themeItem.titleId),
                        selected = themeItem.theme == state.selectedTheme,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                    ) {
                        accept(SettingsEvent.SetTheme(themeItem.theme))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        SettingsItem(
            title = stringResource(Res.string.applicationSettings)
        ) {
            Row(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)
            ) {
                Text(
                    text = state.dailyGoal.toString(),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Column(
                    modifier = Modifier.padding(start = 24.dp)
                ) {
                    ControlButton(
                        isEnabled = true,
                        text = stringResource(Res.string.plus),
                    ) {
                        accept(SettingsEvent.DailyGoal.Up)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    ControlButton(
                        isEnabled = state.dailyGoal > 0,
                        text = stringResource(Res.string.minus),
                    ) {
                        accept(SettingsEvent.DailyGoal.Down)
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsItem(
    title: String,
    content: @Composable () -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.body1
    )
    Spacer(modifier = Modifier.height(24.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        elevation = 4.dp
    ) {
        content()
    }
}

@Composable
private fun ControlButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(38.dp)
            .width(38.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        shape = RoundedCornerShape(16.dp),
        enabled = isEnabled
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = text,
            style = MaterialTheme.typography.button
        )
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
