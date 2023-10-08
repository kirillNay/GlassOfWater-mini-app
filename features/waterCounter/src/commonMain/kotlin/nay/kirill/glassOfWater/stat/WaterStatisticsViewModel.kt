package nay.kirill.glassOfWater.stat

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nay.kirill.healthcare.domain.useCases.GetAllParamsUseCase
import nay.kirill.kmpArch.ViewModel

class WaterStatisticsViewModel(
    private val getAllParamsUseCase: GetAllParamsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<WaterStatisticsState>(WaterStatisticsState.Loading)
    val state: StateFlow<WaterStatisticsState> = _state

    init {
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
}