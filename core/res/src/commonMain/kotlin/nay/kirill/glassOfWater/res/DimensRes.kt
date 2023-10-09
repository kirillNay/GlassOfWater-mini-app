package nay.kirill.glassOfWater.res

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
expect fun dimenRes(id: Int): Dp

expect val Res.dimens.horizontalPadding: Int

expect val Res.dimens.verticalPadding: Int