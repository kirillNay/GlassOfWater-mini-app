package nay.kirill.glassOfWater.data

import com.kirillNay.telegram.miniapp.webApp.webApp
import kotlinx.serialization.json.Json
import nay.kirill.glassOfWater.data.ConfigRepositoryImpl.Companion.CONFIG_KEY
import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository

actual class HealthParamsRepositoryImpl : HealthParamsRepository {

    actual override suspend fun getParamByDate(date: String): Result<HealthParams> =
        webApp.cloudStorage.getItem(date).mapCatching { result ->
            if (result.isEmpty()) {
                HealthParams(0, date)
            } else {
                Json.decodeFromString(HealthParams.serializer(), result)
            }
        }

    actual override suspend fun setParam(date: String, params: HealthParams) {
        webApp.cloudStorage.setItem(date, Json.encodeToString(HealthParams.serializer(), params))
    }

    actual override suspend fun getAllParams(): List<HealthParams> = webApp.cloudStorage.getKeys()
        .getOrNull()
        .orEmpty()
        .mapNotNull { key ->
            webApp.cloudStorage.getItem(key).mapCatching { Json.decodeFromString(HealthParams.serializer(), it) }.getOrNull()
        }

    actual override suspend fun clearParams() {
        val keys = webApp.cloudStorage.getKeys().getOrNull().orEmpty().filter { it != CONFIG_KEY }
        webApp.cloudStorage.removeItems(*keys.toTypedArray())
    }

}
