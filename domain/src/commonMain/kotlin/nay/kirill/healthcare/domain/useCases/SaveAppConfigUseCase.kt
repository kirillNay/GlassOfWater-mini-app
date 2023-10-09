package nay.kirill.healthcare.domain.useCases

import nay.kirill.healthcare.domain.AppConfig
import nay.kirill.healthcare.domain.repositories.ConfigRepository

class SaveAppConfigUseCase(
    private val configRepository: ConfigRepository
) {

    suspend operator fun invoke(config: AppConfig) = configRepository.saveConfig(config)

}