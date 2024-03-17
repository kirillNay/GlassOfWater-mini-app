package nay.kirill.settings

import nay.kirill.glassOfWater.navigation.Navigation
import nay.kirill.healthcare.domain.useCases.GetAppConfigUseCase
import nay.kirill.healthcare.domain.useCases.SaveAppConfigUseCase

actual class SettingsViewModel(
    getAppConfigUseCase: GetAppConfigUseCase,
    saveAppConfigUseCase: SaveAppConfigUseCase,
    navigation: Navigation
) : BaseSettingsViewModel(getAppConfigUseCase, saveAppConfigUseCase, navigation)