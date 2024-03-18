package nay.kirill.glassOfWater.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    h3 = TextStyle(
        color = colors.onBackground,
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold
    ),
    h4 = TextStyle(
        color = colors.onBackground.copy(alpha = 0.5F),
        fontSize = 14.sp,
        fontWeight = FontWeight.Light
    ),
    body2 = TextStyle(
        color = colors.onBackground,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal
    ),
    caption = TextStyle(
        color = colors.onBackground,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal
    ),
    overline = TextStyle(
        color = colors.onBackground.copy(alpha = 0.5F),
        fontSize = 9.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
)