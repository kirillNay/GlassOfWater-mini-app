package nay.kirill.glassOfWater.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nay.kirill.glassOfWater.ui.icons.BackIcon

@Composable
fun StatusBar(
    modifier: Modifier = Modifier,
    text: String,
    backAction: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(
            modifier = Modifier.then(Modifier.size(24.dp)),
            onClick = { backAction() }
        ) {
            Icon(
                imageVector = BackIcon,
                contentDescription = "Back",
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = text,
            style = MaterialTheme.typography.h2
        )
    }
}