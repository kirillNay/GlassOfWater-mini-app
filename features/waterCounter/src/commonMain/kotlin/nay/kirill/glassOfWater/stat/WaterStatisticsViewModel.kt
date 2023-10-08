package nay.kirill.glassOfWater.stat

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.useCases.GetAllParamsUseCase
import nay.kirill.kmpArch.ViewModel

class WaterStatisticsViewModel(
    private val getAllParamsUseCase: GetAllParamsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<WaterStatisticsState>(WaterStatisticsState.Loading)
    val state: StateFlow<WaterStatisticsState> = _state

    init {
        viewModelScope.launch {
            val params = listOf(
                HealthParams(2, "10-09"),
                HealthParams(12, "10-08"),
                HealthParams(3, "10-07"),
                HealthParams(4, "10-06"),
                HealthParams(2, "10-05")
            )

            _state.value = WaterStatisticsState.Content(
                paramsList = params,
            )

//            val params = getAllParamsUseCase()
//            if (params.isEmpty()) {
//                _state.value = WaterStatisticsState.Empty
//            } else {
//                _state.value = WaterStatisticsState.Content(params, listOf())
//            }
        }
    }
}