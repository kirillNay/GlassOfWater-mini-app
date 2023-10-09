package di

import nay.kirill.glassOfWater.counter.GlassOfWaterViewModel
import nay.kirill.glassOfWater.data.ConfigRepositoryImpl
import nay.kirill.glassOfWater.data.HealthParamsRepositoryImpl
import nay.kirill.glassOfWater.stat.WaterStatisticsViewModel
import nay.kirill.healthcare.domain.repositories.ConfigRepository
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository
import nay.kirill.healthcare.domain.useCases.ClearParamsUseCase
import nay.kirill.healthcare.domain.useCases.GetAllParamsUseCase
import nay.kirill.healthcare.domain.useCases.GetAppConfigUseCase
import nay.kirill.healthcare.domain.useCases.GetTodayParamsUseCase
import nay.kirill.healthcare.domain.useCases.MockParamsUseCase
import nay.kirill.healthcare.domain.useCases.SaveAppConfigUseCase
import nay.kirill.healthcare.domain.useCases.UpdateTodayWaterUseCase
import nay.kirill.kmpArch.navigation.NavigationStack
import nay.kirill.kmpArch.navigation.Screen
import nay.kirill.settings.SettingsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::GetTodayParamsUseCase)
    singleOf(::UpdateTodayWaterUseCase)
    singleOf(::GetAllParamsUseCase)
    singleOf(::ClearParamsUseCase)
    singleOf(::MockParamsUseCase)

    singleOf(::GetAppConfigUseCase)
    singleOf(::SaveAppConfigUseCase)

    singleOf(::HealthParamsRepositoryImpl).bind<HealthParamsRepository>()
    singleOf(::ConfigRepositoryImpl).bind<ConfigRepository>()

    factoryOf(::GlassOfWaterViewModel)
    factoryOf(::WaterStatisticsViewModel)
    factoryOf(::SettingsViewModel)

    // App state
    single { NavigationStack(Screen.COUNTER.route) }
}