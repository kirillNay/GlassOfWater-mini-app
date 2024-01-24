package nay.kirill.glassOfWater.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val StatsIconLight: ImageVector
    get() {
        if (statsIconLight != null) {
            return statsIconLight!!
        }
        statsIconLight = Builder(name = "Statistics-svgrepo-com", defaultWidth =
                800.0.dp, defaultHeight = 800.0.dp, viewportWidth = 465.797f, viewportHeight =
                465.797f).apply {
            path(fill = SolidColor(Color(0xFF005FB0)), stroke = SolidColor(Color(0xFF005FB0)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(423.532f, 0.0f)
                horizontalLineTo(42.264f)
                curveToRelative(-11.046f, 0.0f, -20.0f, 8.954f, -20.0f, 20.0f)
                verticalLineToRelative(282.563f)
                curveToRelative(0.0f, 11.046f, 8.954f, 20.0f, 20.0f, 20.0f)
                horizontalLineToRelative(125.651f)
                lineToRelative(-50.474f, 115.203f)
                curveToRelative(-4.433f, 10.117f, 0.175f, 21.912f, 10.293f, 26.346f)
                curveToRelative(2.612f, 1.143f, 5.335f, 1.686f, 8.016f, 1.686f)
                curveToRelative(7.701f, 0.0f, 15.041f, -4.474f, 18.329f, -11.979f)
                lineToRelative(57.507f, -131.256f)
                horizontalLineToRelative(42.625f)
                lineToRelative(57.507f, 131.256f)
                curveToRelative(3.289f, 7.506f, 10.628f, 11.979f, 18.329f, 11.979f)
                curveToRelative(2.68f, 0.0f, 5.404f, -0.542f, 8.016f, -1.686f)
                curveToRelative(10.118f, -4.434f, 14.726f, -16.228f, 10.293f, -26.346f)
                lineToRelative(-50.474f, -115.203f)
                horizontalLineToRelative(125.651f)
                curveToRelative(11.046f, 0.0f, 20.0f, -8.954f, 20.0f, -20.0f)
                verticalLineTo(20.0f)
                curveTo(443.532f, 8.954f, 434.578f, 0.0f, 423.532f, 0.0f)
                close()
                moveTo(151.724f, 246.587f)
                curveToRelative(0.0f, 3.879f, -3.144f, 7.023f, -7.023f, 7.023f)
                horizontalLineToRelative(-30.433f)
                curveToRelative(-3.879f, 0.0f, -7.023f, -3.144f, -7.023f, -7.023f)
                verticalLineTo(93.587f)
                curveToRelative(0.0f, -3.879f, 3.145f, -7.023f, 7.023f, -7.023f)
                horizontalLineToRelative(30.433f)
                curveToRelative(3.879f, 0.0f, 7.023f, 3.144f, 7.023f, 7.023f)
                verticalLineTo(246.587f)
                close()
                moveTo(220.667f, 246.587f)
                curveToRelative(0.0f, 3.879f, -3.144f, 7.023f, -7.023f, 7.023f)
                horizontalLineTo(183.21f)
                curveToRelative(-3.879f, 0.0f, -7.023f, -3.144f, -7.023f, -7.023f)
                verticalLineTo(133.011f)
                curveToRelative(0.0f, -3.879f, 3.144f, -7.023f, 7.023f, -7.023f)
                horizontalLineToRelative(30.433f)
                curveToRelative(3.879f, 0.0f, 7.023f, 3.144f, 7.023f, 7.023f)
                verticalLineTo(246.587f)
                close()
                moveTo(289.609f, 246.587f)
                curveToRelative(0.0f, 3.879f, -3.144f, 7.023f, -7.023f, 7.023f)
                horizontalLineToRelative(-30.433f)
                curveToRelative(-3.879f, 0.0f, -7.023f, -3.144f, -7.023f, -7.023f)
                verticalLineToRelative(-78.283f)
                curveToRelative(0.0f, -3.879f, 3.144f, -7.023f, 7.023f, -7.023f)
                horizontalLineToRelative(30.433f)
                curveToRelative(3.879f, 0.0f, 7.023f, 3.145f, 7.023f, 7.023f)
                verticalLineTo(246.587f)
                close()
                moveTo(358.552f, 246.587f)
                curveToRelative(0.0f, 3.879f, -3.144f, 7.023f, -7.023f, 7.023f)
                horizontalLineToRelative(-30.433f)
                curveToRelative(-3.879f, 0.0f, -7.023f, -3.144f, -7.023f, -7.023f)
                verticalLineToRelative(-42.729f)
                curveToRelative(0.0f, -3.879f, 3.144f, -7.023f, 7.023f, -7.023f)
                horizontalLineToRelative(30.433f)
                curveToRelative(3.879f, 0.0f, 7.023f, 3.145f, 7.023f, 7.023f)
                verticalLineTo(246.587f)
                close()
            }
        }
        .build()
        return statsIconLight!!
    }

private var statsIconLight: ImageVector? = null
