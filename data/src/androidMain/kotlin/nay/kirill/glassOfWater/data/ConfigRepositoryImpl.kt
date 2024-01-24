package nay.kirill.glassOfWater.data

import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nay.kirill.healthcare.domain.AppConfig
import nay.kirill.healthcare.domain.repositories.ConfigRepository

actual class ConfigRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : ConfigRepository {

    private val cachedConfig = MutableSharedFlow<AppConfig>()

    actual override suspend fun saveConfig(config: AppConfig) {
        sharedPreferences.edit()
            .putString(APP_CONFIG_KEY, Json.encodeToString(config))
            .apply()
        cachedConfig.emit(config)
    }

    actual override suspend fun getConfig(): Result<AppConfig> = runCatching {
        sharedPreferences
            .getString(APP_CONFIG_KEY, null)
            ?.let { Json.decodeFromString(it) }
            ?: AppConfig.default
    }

    actual override fun observeConfig(): Flow<AppConfig> = cachedConfig

    companion object {

        private const val APP_CONFIG_KEY = "APP_CONFIG_KEY"

    }

}