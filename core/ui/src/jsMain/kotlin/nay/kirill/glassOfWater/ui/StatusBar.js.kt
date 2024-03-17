package nay.kirill.glassOfWater.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kirillNay.telegram.miniapp.webApp.EventType
import com.kirillNay.telegram.miniapp.webApp.webApp

@Composable
actual fun StatusBar(
    modifier: Modifier,
    text: String,
    backAction: () -> Unit
) {
    var showBackButton: Boolean by remember { mutableStateOf(!webApp.isExpanded) }
    LaunchedEffect(true) {
        webApp.addEventHandler(EventType.VIEWPORT_CHANGED) {
            showBackButton = !webApp.isExpanded
        }
    }
    ActualStatusBar(modifier, text, showBackButton, backAction)
}