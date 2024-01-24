package nay.kirill.glassOfWater.data

import kotlinx.coroutines.flow.Flow
import nay.kirill.healthcare.domain.AppConfig
import nay.kirill.healthcare.domain.repositories.ConfigRepository

actual class ConfigRepositoryImpl : ConfigRepository {

    actual override suspend fun saveConfig(config: AppConfig) {
        TODO("Not yet implemented")
    }

    actual override suspend fun getConfig(): Result<AppConfig> {
        TODO("Not yet implemented")
    }

    actual override fun observeConfig(): Flow<AppConfig> {
        TODO("Not yet implemented")
    }

}