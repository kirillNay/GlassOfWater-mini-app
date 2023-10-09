package nay.kirill.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.adaptiveTheme
import nay.kirill.glassOfWater.res.clearDataButton
import nay.kirill.glassOfWater.res.clearDataConfirmation
import nay.kirill.glassOfWater.res.horizontalPadding
import nay.kirill.glassOfWater.res.mockDataButton
import nay.kirill.glassOfWater.res.mockDataConfirmation
import nay.kirill.glassOfWater.res.settings
import nay.kirill.glassOfWater.res.stringResource
import nay.kirill.glassOfWater.res.verticalPadding

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = Res.dimens.horizontalPadding.dp,
                vertical = Res.dimens.verticalPadding.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.settings),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 28.dp)
        )

        when (val currentState = state) {
            is SettingsState.Content -> Content(
                state = currentState,
                viewModel = viewModel,
                modifier = Modifier.align(Alignment.Start)
            )
            else -> Unit
        }
    }
}

@Composable
private fun Content(
    state: SettingsState.Content,
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Switch(
                checked = state.isAdaptiveBoolean,
                onCheckedChange = {
                    viewModel.updateAdaptiveTheme()
                },
                modifier = Modifier.padding(end = 16.dp),
            )
            Text(
                text = stringResource(Res.string.adaptiveTheme),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center
            )
        }

        val mockDataConfirmationText = stringResource(Res.string.mockDataConfirmation)
        Button(
            modifier = Modifier
                .height(54.dp)
                .padding(bottom = 12.dp),
            onClick = { viewModel.mockData(mockDataConfirmationText) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = stringResource(Res.string.mockDataButton),
                style = MaterialTheme.typography.button
            )
        }

        val clearDataConfirmationText = stringResource(Res.string.clearDataConfirmation)
        Button(
            modifier = Modifier
                .height(54.dp)
                .padding(bottom = 12.dp),
            onClick = { viewModel.clearData(clearDataConfirmationText) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = stringResource(Res.string.clearDataButton),
                style = MaterialTheme.typography.button
            )
        }
    }
}