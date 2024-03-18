package nay.kirill.glassOfWater.res

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp

@Composable
actual fun dimenRes(id: Int): Dp = dimensionResource(id = id)

actual val Res.dimens.horizontalPadding: Int get() = R.dimen.horizontal_padding

actual val Res.dimens.verticalPadding: Int get() = R.dimen.vertical_padding