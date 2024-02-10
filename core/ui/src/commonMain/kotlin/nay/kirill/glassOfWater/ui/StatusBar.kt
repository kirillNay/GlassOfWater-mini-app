package nay.kirill.glassOfWater.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import nay.kirill.glassOfWater.ui.icons.BackIcon

@Composable
fun StatusBar(
    modifier: Modifier = Modifier,
    text: String,
    backAction: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = BackIcon,
            contentDescription = "Back",
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterStart)
                .clip(RoundedCornerShape(30.dp))
                .clickable { backAction() }
                .padding(12.dp)
        )
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = text,
            style = MaterialTheme.typography.h1
        )
    }
}