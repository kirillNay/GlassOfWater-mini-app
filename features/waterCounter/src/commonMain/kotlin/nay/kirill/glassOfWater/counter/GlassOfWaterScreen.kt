package nay.kirill.glassOfWater.counter

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.appName
import nay.kirill.glassOfWater.res.horizontalPadding
import nay.kirill.glassOfWater.res.minus
import nay.kirill.glassOfWater.res.plus
import nay.kirill.glassOfWater.res.stringResource
import nay.kirill.glassOfWater.res.verticalPadding

@Composable
fun GlassOfWaterScreen(
    viewModel: GlassOfWaterViewModel
) {
    val state by viewModel.state.collectAsState()

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = Res.dimens.horizontalPadding.dp,
                        vertical = Res.dimens.verticalPadding.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var isPlaying: Boolean by remember { mutableStateOf(true) }
                val progress by animateFloatAsState(
                    targetValue = if (isPlaying) 0F else 1F,
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing), label = ""
                )
                LaunchedEffect(progress) {
                    if (isPlaying && progress == 0F) {
                        isPlaying = false
                    }
                }

                Text(
                    text = stringResource(Res.string.appName),
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(100.dp))
                WaterAnimation(
                    modifier = Modifier.height(100.dp),
                    progress = progress
                )
                Spacer(modifier = Modifier.height(74.dp))
                if (state is GlassOfWaterState.Content) {
                    Content(state as GlassOfWaterState.Content, viewModel) { isPlaying = true }
                }
            }
        }
    }
}

@Composable
private fun Content(
    state: GlassOfWaterState.Content,
    viewModel: GlassOfWaterViewModel,
    onUp: () -> Unit
) {
    Text(
        text = state.count.toString(),
        style = MaterialTheme.typography.h6
    )
    Spacer(modifier = Modifier.height(24.dp))
    Controllers(
        onDown = { viewModel.decreaseCount() },
        onUp = {
            viewModel.increaseCount()
            onUp()
        },
        isDownEnabled = state.isDecreaseEnabled
    )
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

@Composable
expect fun WaterAnimation(
    modifier: Modifier = Modifier,
    progress: Float
)