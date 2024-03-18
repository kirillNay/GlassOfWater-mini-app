package nay.kirill.glassOfWater.stat

import com.kirillNay.telegram.miniapp.webApp.webApp
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.healthcare.domain.useCases.GetAllParamsUseCase
import nay.kirill.healthcare.domain.useCases.GetAppConfigUseCase

actual class WaterStatisticsViewModel(
    getAllParamsUseCase: GetAllParamsUseCase,
    getAppConfigUseCase: GetAppConfigUseCase,
    navigation: Navigation
) : BaseWaterStatisticsViewModel(getAllParamsUseCase, getAppConfigUseCase, navigation) {

    init {
        webApp.backButton.onClick(::back).show()
    }

    override fun onDispose() {
        super.onDispose()
        webApp.backButton.offClick(::back).hide()
    }

}