package nay.kirill.glassOfWater.stat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import nay.kirill.glassOfWater.navigation.SharedScreens
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.dimenRes
import nay.kirill.glassOfWater.res.horizontalPadding
import nay.kirill.glassOfWater.res.noStats
import nay.kirill.glassOfWater.res.stats
import nay.kirill.glassOfWater.res.stringResource
import nay.kirill.glassOfWater.res.verticalPadding
import nay.kirill.glassOfWater.ui.ErrorState
import nay.kirill.glassOfWater.ui.StatusBar

val statsScreenModule = screenModule {
    register<SharedScreens.Stats> {
        WaterStatisticsScreen()
    }
}

class WaterStatisticsScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: WaterStatisticsViewModel = getScreenModel()
        val state by viewModel.state.collectAsState()

        WaterStatisticsContent(
            state = state,
            accept = viewModel::reduce
        )
    }

}

@Composable
private fun WaterStatisticsContent(
    state: WaterStatisticsState,
    accept: (WaterStatisticsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatusBar(
            text = stringResource(Res.string.stats),
            modifier = Modifier.padding(
                start = dimenRes(Res.dimens.horizontalPadding),
                end = dimenRes(Res.dimens.horizontalPadding),
                top = dimenRes(Res.dimens.verticalPadding),
                bottom = 36.dp
            ),
            backAction = { accept(WaterStatisticsEvent.Back) }
        )

        Box(
            modifier = Modifier.fillMaxSize().padding(top = 12.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            State(isVisible = state is WaterStatisticsState.Content) {
                Content(
                    state as WaterStatisticsState.Content,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(6.dp),
                    accept = accept
                )
            }

            State(isVisible = state is WaterStatisticsState.Empty) {
                ErrorState(
                    text = stringResource(Res.string.noStats),
                    modifier = Modifier.padding(top = 46.dp)
                )
            }

            State(isVisible = state is WaterStatisticsState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(top = 50.dp)
                )
            }
        }
    }
}

@Composable
private fun State(
    isVisible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 300, easing = LinearEasing)),
        exit = fadeOut(animationSpec = tween(durationMillis = 150)),
    ) {
        content()
    }
}

@Composable
private fun Content(
    state: WaterStatisticsState.Content,
    modifier: Modifier = Modifier,
    accept: (WaterStatisticsEvent) -> Unit
) {
    Column {
        Card(
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            elevation = 4.dp
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .align(Alignment.CenterHorizontally),
                    text = state.weekText,
                    style = MaterialTheme.typography.h4
                )

                BarChart(
                    state = state,
                    modifier = Modifier.padding(start = 12.dp, top = 18.dp, bottom = 18.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Controller(
                modifier = Modifier.size(44.dp),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Previous week",
                isAvailable = state.isPrevWeekAvailable
            ) {
                accept(WaterStatisticsEvent.Week.Decrease)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Controller(
                modifier = Modifier.padding(start = 12.dp).size(44.dp),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Next week",
                isAvailable = state.isNextWeekAvailable
            ) {
                accept(WaterStatisticsEvent.Week.Increase)
            }
        }
    }
}

@Composable
private fun Controller(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    isAvailable: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isAvailable,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            disabledBackgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.2F)
        ),
        shape = CircleShape,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
        )
    }
}
