package nay.kirill.glassOfWater.res

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun buildDimenResources(): Map<Int, Dp> {
    val dps = mutableMapOf<Int, Dp>()
    val rs = Res.dimens

    dps[rs.horizontalPadding] = 16.dp
    dps[rs.verticalPadding] = 16.dp

    return dps
}