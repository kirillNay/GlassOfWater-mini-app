package nay.kirill.glassOfWater.counter

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import nay.kirill.glassOfWater.navigation.SharedScreens
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.dimenRes
import nay.kirill.glassOfWater.res.horizontalPadding
import nay.kirill.glassOfWater.res.mainTitle
import nay.kirill.glassOfWater.res.plus
import nay.kirill.glassOfWater.res.stringResource
import nay.kirill.glassOfWater.res.verticalPadding
import nay.kirill.glassOfWater.ui.ErrorState
import nay.kirill.glassOfWater.ui.icons.IconStats

val counterScreenModule = screenModule {
    register<SharedScreens.Counter> {
        WaterCounterScreen()
    }
}

class WaterCounterScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: WaterCounterViewModel = getScreenModel()
        val state by viewModel.state.collectAsState()

        WaterCounter(state, viewModel::accept)
    }

}

@Composable
internal fun WaterCounter(
    state: WaterCounterState,
    accept: (WaterCounterEvent) -> Unit
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
        Text(
            text = stringResource(Res.string.mainTitle),
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(bottom = 64.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is WaterCounterState.Content -> Content(state, accept)
                is WaterCounterState.Error -> ErrorState(
                    modifier = Modifier.padding(top = 46.dp)
                )

                else -> Unit
            }
        }
    }
}

@Composable
private fun Content(
    state: WaterCounterState.Content,
    accept: (WaterCounterEvent) -> Unit
) {
    val progress by animateFloatAsState(
        targetValue = state.progress,
        animationSpec = tween(easing = LinearEasing),
        label = ""
    )

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            WaterAnimation(
                modifier = Modifier.size(250.dp),
                progress = progress
            )
            Spacer(modifier = Modifier.height(74.dp))
            Row {
                Text(
                    text = state.count.toString(),
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "/ " + state.dailyGoal.toString(),
                    style = MaterialTheme.typography.caption
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            ControlButton(
                modifier = Modifier.size(64.dp),
                text = stringResource(Res.string.plus)
            ) {
                accept(WaterCounterEvent.IncreaseCount)
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 56.dp)
        ) {
            IconButton(
                onClick = { accept(WaterCounterEvent.OpenStats) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = IconStats,
                        contentDescription = "Statistics",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
            IconButton(
                onClick = { accept(WaterCounterEvent.OpenSettings) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun ControlButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        elevation = 8.dp
    ) {
        Button(
            modifier = Modifier
                .fillMaxSize(),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            ),
            shape = CircleShape,
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.button
            )
        }
    }
}
