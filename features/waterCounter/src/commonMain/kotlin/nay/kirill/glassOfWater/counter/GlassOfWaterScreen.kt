package nay.kirill.glassOfWater.counter

import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import nay.kirill.glassOfWater.res.appName
import nay.kirill.glassOfWater.res.dimenRes
import nay.kirill.glassOfWater.res.horizontalPadding
import nay.kirill.glassOfWater.res.minus
import nay.kirill.glassOfWater.res.plus
import nay.kirill.glassOfWater.res.stringResource
import nay.kirill.glassOfWater.res.verticalPadding
import nay.kirill.glassOfWater.ui.ErrorState
import nay.kirill.glassOfWater.ui.StatsIconLight

val counterScreenModule = screenModule {
    register<SharedScreens.Counter> {
        GlassOfWaterScreen()
    }
}

class GlassOfWaterScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: GlassOfWaterViewModel = getScreenModel()
        val state by viewModel.state.collectAsState()

        GlassOfWater(state, viewModel::accept)
    }

}

@Composable
internal fun GlassOfWater(
    state: GlassOfWaterState,
    accept: (CounterEvent) -> Unit
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
            text = stringResource(Res.string.appName),
            style = MaterialTheme.typography.h4,
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
                is GlassOfWaterState.Content -> Content(state, accept)
                is GlassOfWaterState.Error -> ErrorState(
                    modifier = Modifier.padding(top = 46.dp)
                )

                else -> Unit
            }
        }
    }
}

@Composable
private fun Content(
    state: GlassOfWaterState.Content,
    accept: (CounterEvent) -> Unit
) {
    var isPlaying: Boolean by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (isPlaying) 0F else 1F,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = ""
    )
    LaunchedEffect(progress) {
        if (isPlaying && progress == 0F) {
            isPlaying = false
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            WaterAnimation(
                modifier = Modifier.height(170.dp),
                progress = progress
            )
            Spacer(modifier = Modifier.height(74.dp))
            Text(
                text = state.count.toString(),
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(24.dp))
            Controllers(
                onDown = { accept(CounterEvent.DecreaseCount) },
                onUp = {
                    accept(CounterEvent.IncreaseCount)
                    isPlaying = true
                },
                isDownEnabled = state.count > 0
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 56.dp)
        ) {
            IconButton(
                onClick = { accept(CounterEvent.OpenStats) },
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
                        imageVector = StatsIconLight,
                        contentDescription = "Statistics",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
            IconButton(
                onClick = { accept(CounterEvent.OpenSettings) },
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
private fun Controllers(
    onDown: () -> Unit,
    onUp: () -> Unit,
    isDownEnabled: Boolean
) {
    Row {
        ControlButton(
            onDown,
            stringResource(Res.string.minus),
            isEnabled = isDownEnabled
        )
        Spacer(modifier = Modifier.width(48.dp))
        ControlButton(
            onUp,
            stringResource(Res.string.plus)
        )
    }
}

@Composable
private fun ControlButton(
    onClick: () -> Unit,
    text: String,
    isEnabled: Boolean = true
) {
    Button(
        modifier = Modifier
            .height(44.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        shape = RoundedCornerShape(16.dp),
        enabled = isEnabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button
        )
    }
}
