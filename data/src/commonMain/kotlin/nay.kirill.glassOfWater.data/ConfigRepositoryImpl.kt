package nay.kirill.glassOfWater.data

import nay.kirill.healthcare.domain.AppConfig
import nay.kirill.healthcare.domain.repositories.ConfigRepository

expect class ConfigRepositoryImpl : ConfigRepository {

    override suspend fun getConfig(): Result<AppConfig>

    override suspend fun saveConfig(config: AppConfig)

}