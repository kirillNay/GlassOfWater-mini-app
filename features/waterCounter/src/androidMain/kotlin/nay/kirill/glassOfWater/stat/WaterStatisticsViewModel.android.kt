package nay.kirill.glassOfWater.stat

import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.healthcare.domain.useCases.GetAllParamsUseCase
import nay.kirill.healthcare.domain.useCases.GetAppConfigUseCase

actual class WaterStatisticsViewModel(
    getAllParamsUseCase: GetAllParamsUseCase,
    getAppConfigUseCase: GetAppConfigUseCase,
    navigation: Navigation
) : BaseWaterStatisticsViewModel(getAllParamsUseCase, getAppConfigUseCase, navigation)
