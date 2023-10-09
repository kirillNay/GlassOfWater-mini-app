package nay.kirill.healthcare.domain.repositories

import nay.kirill.healthcare.domain.AppConfig

interface ConfigRepository {

    suspend fun saveConfig(config: AppConfig)

    suspend fun getConfig(): Result<AppConfig>

}