package nay.kirill.glassOfWater.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTextStyle {

    val Header = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.W700,
            fontFamily = FontFamily.SansSerif,
            lineHeight = 30.sp,
            color = AppColors.BlackText
    )

    val Header2 = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.W400,
            fontFamily = FontFamily.SansSerif,
            lineHeight = 42.sp,
            color = AppColors.Primary
    )

    val Highlighted = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight.W700,
            fontFamily = FontFamily.SansSerif,
            lineHeight = 30.sp,
            color = AppColors.BlackText
    )

    val SubTitle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.W300,
            fontFamily = FontFamily.SansSerif,
            lineHeight = 26.sp,
            color = AppColors.GreyText
    )

    val SubTitleHighlighted = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
            fontFamily = FontFamily.SansSerif,
            lineHeight = 26.sp,
            color = AppColors.GreyText
    )

    val ButtonStyle = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = AppColors.OnPrimary
    )

    val ListTitleStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W300,
            fontFamily = FontFamily.SansSerif,
            lineHeight = 26.sp,
            color = AppColors.GreyText
    )

    val ListTextStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            fontFamily = FontFamily.SansSerif,
            lineHeight = 16.sp,
            color = AppColors.ListTextColor
    )

}