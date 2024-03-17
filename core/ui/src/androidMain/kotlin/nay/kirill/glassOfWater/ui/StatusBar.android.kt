package nay.kirill.glassOfWater.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun StatusBar(
    modifier: Modifier,
    text: String,
    backAction: () -> Unit
) {
    ActualStatusBar(modifier, text, true, backAction)
}