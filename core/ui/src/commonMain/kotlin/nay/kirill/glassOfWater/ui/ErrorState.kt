package nay.kirill.glassOfWater.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import nay.kirill.glassOfWater.res.Res
import nay.kirill.glassOfWater.res.commonError
import nay.kirill.glassOfWater.res.stringResource

@Composable
fun ErrorState(
    text: String = stringResource(Res.string.commonError),
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
        )
    }
}