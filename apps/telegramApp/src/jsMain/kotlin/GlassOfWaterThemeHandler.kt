import androidx.compose.material.Colors
import com.kirillNay.telegram.miniapp.compose.theme.TelegramColors
import com.kirillNay.telegram.miniapp.compose.theme.ThemeHandler
import com.kirillNay.telegram.miniapp.webApp.EventType
import com.kirillNay.telegram.miniapp.webApp.webApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import nay.kirill.healthcare.domain.useCases.ObserveAppConfigUseCase

class GlassOfWaterThemeHandler(
    observeAppConfigUseCase: ObserveAppConfigUseCase
) : ThemeHandler {

    private val converter = GlassOfWaterConverter()

    private val coroutineScope: CoroutineScope = MainScope()

    private val _colors = MutableStateFlow(
        converter.convert(TelegramColors.fromWebApp(), webApp.colorScheme, false)
    )
    override val colors: StateFlow<Colors> = _colors

    private val themeChangedFlow = MutableStateFlow(Unit)

    init {
        webApp.addEventHandler(EventType.THEME_CHANGED) { themeChangedFlow.tryEmit(Unit) }
        themeChangedFlow.combine(observeAppConfigUseCase()) { _, appConfig ->
            converter.convert(TelegramColors.fromWebApp(), webApp.colorScheme, appConfig.isAdaptiveTheme)
        }
            .onEach { _colors.value = it }
            .launchIn(coroutineScope)
    }

}