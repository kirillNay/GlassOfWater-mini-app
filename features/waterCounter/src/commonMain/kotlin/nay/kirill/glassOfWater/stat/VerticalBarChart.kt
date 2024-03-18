package nay.kirill.glassOfWater.stat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.friday
import nay.kirill.glassOfWater.res.monday
import nay.kirill.glassOfWater.res.saturday
import nay.kirill.glassOfWater.res.stringResource
import nay.kirill.glassOfWater.res.sunday
import nay.kirill.glassOfWater.res.thursday
import nay.kirill.glassOfWater.res.tuesday
import nay.kirill.glassOfWater.res.wednesday
import kotlin.math.max
import kotlin.math.roundToInt

private val maxChartValue = FirstBaseline

private val minChartValue = LastBaseline

private val midChatValue = HorizontalAlignmentLine(merger = { old, new ->
    max(old, new)
})

@Composable
internal fun BarChart(
    state: WaterStatisticsState.Content,
    modifier: Modifier = Modifier,
) {
    Layout(
        content = {
            ChartIndex(
                text = state.maxIndex.toString()
            )
            ChartIndex(
                text = state.midIndex.toString()
            )
            ChartIndex(
                text = state.minIndex.toString()
            )

            StatBarChart(state, Modifier.height(300.dp))
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

        // Obtain the alignment lines from BarChart to position vertical lines
        val minValueBaseline = barChartPlaceable[minChartValue]
        val maxValueBaseline = barChartPlaceable[maxChartValue]
        val midValueBaseline = barChartPlaceable[midChatValue]

        layout(constraints.maxWidth, constraints.maxHeight) {
            maxTextPlaceable.placeRelative(
                x = 0,
                y = maxValueBaseline - maxTextPlaceable.height / 2
            )
            midTextPlaceable.placeRelative(
                x = 0,
                y = midValueBaseline - midTextPlaceable.height / 2
            )
            minTextPlaceable.placeRelative(
                x = 0,
                y = minValueBaseline - minTextPlaceable.height / 2
            )
            barChartPlaceable.placeRelative(
                x = 50,
                y = 0
            )
        }
    }
}

@Composable
private fun ChartIndex(
    modifier: Modifier = Modifier,
    text: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2
        )
        Box(
            modifier = Modifier
                .padding(end = 6.dp, start = 16.dp)
                .background(Color(0x40CCCCCC))
                .height(1.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun StatBarChart(
    state: WaterStatisticsState.Content,
    modifier: Modifier = Modifier
) {
    val counts = remember(state.statItems) { state.statItems.map { it.count } }

    BoxWithConstraints(modifier = modifier) {
        val density = LocalDensity.current
        with(density) {
            val yPositionRatio = remember(density, maxHeight, state.maxIndex) {
                (maxHeight - 40.dp).toPx() / (state.maxIndex * 1.2F)
            }
            val xPositionRatio = remember(density, maxWidth, counts) {
                maxWidth.toPx() / (counts.size + 1)
            }
            val xOffset = remember(density) { // center points in the graph
                xPositionRatio / (counts.size + 1)
            }

            // Calculate baselines
            val maxYBaseline = remember(counts) {
                state.minIndex * yPositionRatio
            }
            val midYBaseline = remember(counts) {
                state.midIndex * yPositionRatio
            }
            val minYBaseline = remember(counts) {
                state.maxIndex * yPositionRatio
            }

            val chartColor = MaterialTheme.colors.primary
            val accomplishedColor = MaterialTheme.colors.secondary
            val textMeasure = rememberTextMeasurer()
            val textStyle = MaterialTheme.typography.overline.copy(textAlign = TextAlign.Center)
            val dayOfWeeks = listOf(
                stringResource(Res.string.monday),
                stringResource(Res.string.tuesday),
                stringResource(Res.string.wednesday),
                stringResource(Res.string.thursday),
                stringResource(Res.string.friday),
                stringResource(Res.string.saturday),
                stringResource(Res.string.sunday)
            )

            Layout(
                content = {},
                modifier = Modifier.drawBehind {
                    state.statItems.forEachIndexed { index, item ->
                        val dataPoint = item.count
                        val rectSize = Size(15f, dataPoint * yPositionRatio)
                        val topLeftOffset = Offset(
                            x = xPositionRatio * (index + 1) - xOffset,
                            y = (state.maxIndex - dataPoint) * yPositionRatio
                        )
                        drawRoundRect(
                            if (item.isAccomplished) accomplishedColor else chartColor,
                            topLeftOffset,
                            rectSize,
                            CornerRadius(10F)
                        )

                        // Calculate textWidth to set x offset relative
                        val textWidth = textMeasure.measure(text = AnnotatedString(dayOfWeeks[item.dayOfWeek])).size.width
                        drawText(
                            textMeasurer = textMeasure,
                            text = dayOfWeeks[item.dayOfWeek],
                            style = textStyle,
                            topLeft = Offset(
                                x = topLeftOffset.x - textWidth / 4F,
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