package nay.kirill.glassOfWater.data

import com.kirillNay.telegram.miniapp.webApp.webApp
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nay.kirill.healthcare.domain.AppConfig
import nay.kirill.healthcare.domain.repositories.ConfigRepository

actual class ConfigRepositoryImpl : ConfigRepository {

    actual override suspend fun getConfig(): Result<AppConfig> =
        webApp.cloudStorage.getItem(CONFIG_KEY).mapCatching { Json.decodeFromString(it) }

    actual override suspend fun saveConfig(config: AppConfig) {
        webApp.cloudStorage.setItem(CONFIG_KEY, Json.encodeToString(config))
    }

    companion object {

        const val CONFIG_KEY = "CONFIG_KEY"

    }

}