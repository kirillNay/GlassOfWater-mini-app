package nay.kirill.healthcare.domain.repositories

import kotlinx.coroutines.flow.Flow
import nay.kirill.healthcare.domain.AppConfig

interface ConfigRepository {

    suspend fun saveConfig(config: AppConfig)

    suspend fun getConfig(): Result<AppConfig>

    fun observeConfig(): Flow<AppConfig>

}