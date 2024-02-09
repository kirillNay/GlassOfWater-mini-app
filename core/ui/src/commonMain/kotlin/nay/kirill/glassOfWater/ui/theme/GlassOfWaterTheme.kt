package nay.kirill.glassOfWater.ui.theme

import androidx.compose.runtime.Composable

@Composable
expect fun GlassOfWaterTheme(
    theme: UiTheme,
    content: @Composable () -> Unit
)