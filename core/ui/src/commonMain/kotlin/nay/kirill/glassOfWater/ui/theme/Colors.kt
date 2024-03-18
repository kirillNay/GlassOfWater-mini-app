package nay.kirill.glassOfWater.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

fun appLightColors(
    primary: Color = Color(0xFF005FB0),
    onPrimary: Color = Color(0xFFFFFFFF),
    secondaryVariant: Color = Color(0xFF001C3B),
    background: Color = Color.White,
    onBackground: Color = Color.Black,
    secondary: Color = Color(0xFF37BD26),
): Colors = lightColors(
    primary = primary,
    onPrimary = onPrimary,
    secondaryVariant = secondaryVariant,
    background = background,
    onBackground = onBackground,
    secondary = secondary,
)

fun appDarkColors(
    primary: Color = Color(0xFFA6C8FF),
    onPrimary: Color = Color(0xFF00305F),
    secondaryVariant: Color = Color(0xFFD5E3FF),
    background: Color = Color(0xFF121212),
    onBackground: Color = Color.White,
    secondary: Color = Color(0xFF37BD26),
): Colors = darkColors(
    primary = primary,
    onPrimary = onPrimary,
    secondaryVariant = secondaryVariant,
    background = background,
    onBackground = onBackground,
    secondary = secondary,
)