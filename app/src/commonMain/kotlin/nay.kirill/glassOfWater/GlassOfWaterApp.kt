package nay.kirill.glassOfWater

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nay.kirill.glassOfWater.uiKit.AppTextStyle

@Composable
fun GlassOfWaterApp() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .background(Color.Red)
                        .padding(
                            top = 40.dp,
                            start = 16.dp,
                            end = 52.dp
                        ),
                    text = "Подсчет количества воды",
                    style = AppTextStyle.Header2
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}