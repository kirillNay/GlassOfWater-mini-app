package nay.kirill.glassOfWater.stat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.noStats
import nay.kirill.glassOfWater.res.stringResource
import nay.kirill.healthcare.domain.HealthParams
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun StatisticsScreen(
    viewModel: WaterStatisticsViewModel
) {
    val state by viewModel.state.collectAsState()

    when (val currentState = state) {
        is WaterStatisticsState.Content -> Content(
            currentState,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        is WaterStatisticsState.Empty -> Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = stringResource(Res.string.noStats),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
        }

        else -> Unit
    }
}

@Composable
private fun Content(
    state: WaterStatisticsState.Content,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        BarChart(
            state.paramsList,
            modifier = Modifier.height(300.dp),
        )
    }
}

private val maxChartValue = FirstBaseline

private val minChartValue = LastBaseline

private val midChatValue = HorizontalAlignmentLine(merger = { old, new ->
    max(old, new)
})

@Composable
private fun BarChart(
    params: List<HealthParams>,
    modifier: Modifier = Modifier,
) {
    val counts = remember(params) { params.map { it.waterCount } }
    val maxValue = remember(params) { params.maxOf { it.waterCount } }
    val midValue = remember(maxValue) { maxValue / 2 }

    Layout(
        content = {
            Text(
                text = counts.max().toString(),
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = midValue.toString(),
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = "0",
                style = MaterialTheme.typography.subtitle1
            )
            StatBarChart(params, Modifier.height(300.dp))
        },
        modifier = modifier
    ) { measurables, constraints ->
        check(measurables.size == 4)
        val placeables = measurables.map {
            it.measure(constraints.copy(minWidth = 0, minHeight = 0))
        }

        val maxTextPlaceable = placeables[0]
        val midTextPlaceable = placeables[1]
        val minTextPlaceable = placeables[2]
        val barChartPlaceable = placeables[3]

        // Obtain the alignment lines from BarChart to position the Text
        val minValueBaseline = barChartPlaceable[minChartValue]
        val maxValueBaseline = barChartPlaceable[maxChartValue]
        val midValueBaseline = barChartPlaceable[midChatValue]
        layout(constraints.maxWidth, constraints.maxHeight) {
            maxTextPlaceable.placeRelative(
                x = 0,
                y = maxValueBaseline - maxTextPlaceable.height
            )
            midTextPlaceable.placeRelative(
                x = 0,
                y = midValueBaseline - midTextPlaceable.height
            )
            minTextPlaceable.placeRelative(
                x = 0,
                y = minValueBaseline - minTextPlaceable.height
            )
            barChartPlaceable.placeRelative(
                x = max(maxTextPlaceable.width, minTextPlaceable.width) + 20,
                y = 0
            )
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun StatBarChart(
    params: List<HealthParams>,
    modifier: Modifier = Modifier
) {
    val counts = remember(params) { params.map { it.waterCount } }

    BoxWithConstraints(modifier = modifier) {
        val density = LocalDensity.current
        val maxValue = remember(counts) { counts.max() * 1.2F }
        with(density) {
            val yPositionRatio = remember(density, maxHeight, maxValue) {
                (maxHeight - 40.dp).toPx() / maxValue
            }
            val xPositionRatio = remember(density, maxWidth, counts) {
                maxWidth.toPx() / (counts.size + 1)
            }
            val xOffset = remember(density) { // center points in the graph
                xPositionRatio / (counts.size + 1)
            }

            // Calculate baselines
            val maxYBaseline = remember(counts) {
                counts.maxOrNull()?.let { (maxValue - it) * yPositionRatio } ?: 0f
            }
            val midYBaseline = remember(counts) {
                (maxValue - (counts.max() / 2)) * yPositionRatio
            }
            val minYBaseline = remember(counts) {
                maxValue * yPositionRatio
            }

            val chartColor = MaterialTheme.colors.primary
            val textMeasure = rememberTextMeasurer()
            val textStyle = MaterialTheme.typography.subtitle2

            Layout(
                content = {},
                modifier = Modifier.drawBehind {
                    params.forEachIndexed { index, param ->
                        val dataPoint = param.waterCount
                        val rectSize = Size(20f, dataPoint * yPositionRatio)
                        val topLeftOffset = Offset(
                            x = xPositionRatio * (index + 1) - xOffset,
                            y = (maxValue - dataPoint) * yPositionRatio
                        )
                        drawRoundRect(chartColor, topLeftOffset, rectSize, CornerRadius(5F))
                        drawText(
                            textMeasurer = textMeasure,
                            text = param.date,
                            style = textStyle,
                            topLeft = Offset(
                                x = xPositionRatio * (index + 1) - xOffset - 15.sp.toPx(),
                                y = (maxHeight - 30.dp).toPx()
                            )
                        )
                    }
                }
            ) { _, constraints ->
                with(constraints) {
                    layout(
                        width = if (hasBoundedWidth) maxWidth else minWidth,
                        height = if (hasBoundedHeight) maxHeight else minHeight,
                        // Custom AlignmentLines are set here. These are propagated
                        // to direct and indirect parent composables.
                        alignmentLines = mapOf(
                            minChartValue to minYBaseline.roundToInt(),
                            midChatValue to midYBaseline.roundToInt(),
                            maxChartValue to maxYBaseline.roundToInt()
                        )
                    ) {}
                }
            }
        }
    }
}
