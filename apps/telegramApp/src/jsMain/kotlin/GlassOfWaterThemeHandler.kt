import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kirillNay.telegram.miniapp.compose.theme.TelegramColors
import com.kirillNay.telegram.miniapp.compose.theme.TelegramThemeHandler
import com.kirillNay.telegram.miniapp.webApp.EventType
import com.kirillNay.telegram.miniapp.webApp.webApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import nay.kirill.healthcare.domain.useCases.ObserveAppConfigUseCase

class GlassOfWaterThemeHandler(
    observeAppConfigUseCase: ObserveAppConfigUseCase
) : TelegramThemeHandler {

    private val converter = GlassOfWaterConverter()

    private val coroutineScope: CoroutineScope = MainScope()

    override var colors by mutableStateOf(converter.convert(TelegramColors.fromWebApp(), webApp.colorScheme, false))

    private val themeChangedFlow = MutableStateFlow(Unit)

    init {
        webApp.addEventHandler(EventType.THEME_CHANGED) { themeChangedFlow.tryEmit(Unit) }
        themeChangedFlow.combine(observeAppConfigUseCase()) { _, appConfig ->
            converter.convert(TelegramColors.fromWebApp(), webApp.colorScheme, appConfig.isAdaptiveTheme)
        }
            .onEach { colors = it }
            .launchIn(coroutineScope)
    }

}