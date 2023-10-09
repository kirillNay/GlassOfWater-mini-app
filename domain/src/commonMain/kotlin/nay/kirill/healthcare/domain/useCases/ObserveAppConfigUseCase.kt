package nay.kirill.healthcare.domain.useCases

import kotlinx.coroutines.flow.Flow
import nay.kirill.healthcare.domain.AppConfig
import nay.kirill.healthcare.domain.repositories.ConfigRepository

class ObserveAppConfigUseCase(
    private val configRepository: ConfigRepository
) {

    operator fun invoke(): Flow<AppConfig> = configRepository.observeConfig()

}