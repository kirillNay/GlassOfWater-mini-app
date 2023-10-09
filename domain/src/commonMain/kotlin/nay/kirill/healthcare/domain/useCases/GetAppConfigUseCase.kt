package nay.kirill.healthcare.domain.useCases

import nay.kirill.healthcare.domain.AppConfig
import nay.kirill.healthcare.domain.repositories.ConfigRepository

class GetAppConfigUseCase(
    private val configRepository: ConfigRepository
) {

    suspend operator fun invoke() = configRepository.getConfig().getOrNull() ?: AppConfig.default

}