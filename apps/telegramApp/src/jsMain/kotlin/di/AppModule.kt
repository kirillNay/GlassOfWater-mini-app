package di

import nay.kirill.glassOfWater.GlassOfWaterViewModel
import nay.kirill.glassOfWater.data.HealthParamsRepositoryImpl
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository
import nay.kirill.healthcare.domain.useCases.GetTodayParamsUseCase
import nay.kirill.healthcare.domain.useCases.UpdateTodayWaterUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::GetTodayParamsUseCase)
    singleOf(::UpdateTodayWaterUseCase)

    singleOf(::HealthParamsRepositoryImpl).bind<HealthParamsRepository>()

    factoryOf(::GlassOfWaterViewModel)
}