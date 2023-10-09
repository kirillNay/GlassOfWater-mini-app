package nay.kirill.glassOfWater.data

import com.kirillNay.telegram.miniapp.webApp.webApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.zip
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nay.kirill.healthcare.domain.AppConfig
import nay.kirill.healthcare.domain.repositories.ConfigRepository

actual class ConfigRepositoryImpl : ConfigRepository {

    private val cachedConfig = MutableSharedFlow<AppConfig>()

    actual override suspend fun getConfig(): Result<AppConfig> =
        webApp.cloudStorage.getItem(CONFIG_KEY).mapCatching { Json.decodeFromString(it) }

    actual override suspend fun saveConfig(config: AppConfig) {
        cachedConfig.emit(config)
        webApp.cloudStorage.setItem(CONFIG_KEY, Json.encodeToString(config))
    }

    actual override fun observeConfig(): Flow<AppConfig> = merge(
        flow { emit(getConfig().getOrDefault(AppConfig.default)) },
        cachedConfig
    )

    companion object {

        const val CONFIG_KEY = "CONFIG_KEY"

    }

}