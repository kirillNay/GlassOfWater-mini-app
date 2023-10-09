package nay.kirill.glassOfWater.stat

import com.kirillNay.telegram.miniapp.webApp.webApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nay.kirill.healthcare.domain.useCases.GetAllParamsUseCase
import nay.kirill.kmpArch.navigation.NavigationStack
import nay.kirill.kmpArch.ViewModel

class WaterStatisticsViewModel(
    private val getAllParamsUseCase: GetAllParamsUseCase,
    private val navigationStack: NavigationStack
) : ViewModel() {

    private val _state = MutableStateFlow<WaterStatisticsState>(WaterStatisticsState.Loading)
    val state: StateFlow<WaterStatisticsState> = _state

    private val back = {
        navigationStack.back()
        onCleared()
    }

    init {
        webApp.backButton
            .onClick(back)
            .show()

        viewModelScope.launch {
            val params = getAllParamsUseCase()
                .sortedBy { it.date }
                .takeLast(5)
                .map { it.copy(date = it.date.drop(5).replace('-', '/')) }
            if (params.isEmpty()) {
                _state.value = WaterStatisticsState.Empty
            } else {
                _state.value = WaterStatisticsState.Content(params)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        webApp.backButton.offClick(back).hide()
    }
}