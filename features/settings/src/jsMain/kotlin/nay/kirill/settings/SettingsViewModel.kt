package nay.kirill.settings

import com.kirillNay.telegram.miniapp.webApp.webApp
import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.healthcare.domain.useCases.GetAppConfigUseCase
import nay.kirill.healthcare.domain.useCases.SaveAppConfigUseCase

actual class SettingsViewModel(
    getAppConfigUseCase: GetAppConfigUseCase,
    saveAppConfigUseCase: SaveAppConfigUseCase,
    navigation: Navigation
) : BaseSettingsViewModel(getAppConfigUseCase, saveAppConfigUseCase, navigation) {

    init {
        webApp.backButton.onClick(::back).show()
    }

    override fun onDispose() {
        super.onDispose()
        webApp.backButton.offClick(::back).hide()
    }

}