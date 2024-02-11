package nay.kirill.glassOfWater.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun appTypography(
    colors: Colors
) = Typography(
    h1 = TextStyle(
        color = colors.onBackground,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
    h2 = TextStyle(
        color = colors.onBackground,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    body1 = TextStyle(
        color = colors.onBackground,
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold
    ),
    body2 = TextStyle(
        color = colors.onBackground,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal
    ),
    caption = TextStyle(
        color = colors.onBackground,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )
)