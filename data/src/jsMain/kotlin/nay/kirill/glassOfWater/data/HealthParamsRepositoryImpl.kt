package nay.kirill.glassOfWater.data

import com.kirillNay.telegram.miniapp.webApp.webApp
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import nay.kirill.glassOfWater.utils.onlyDate
import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository

actual class HealthParamsRepositoryImpl : HealthParamsRepository {

    actual override suspend fun getParamByDate(date: LocalDate): Result<HealthParams> =
        webApp.cloudStorage.getItem(date.toString()).mapCatching { result ->
            if (result.isEmpty()) {
                HealthParams(0, date)
            } else {
                Json.decodeFromString(HealthParams.serializer(), result)
            }
        }

    actual override suspend fun setParam(date: LocalDate, params: HealthParams) {
        webApp.cloudStorage.setItem(date.toString(), Json.encodeToString(HealthParams.serializer(), params))
    }

    actual override suspend fun getAllParams(): List<HealthParams> = webApp.cloudStorage.getKeys()
        .getOrNull()
        .orEmpty()
        .mapNotNull { key ->
            webApp.cloudStorage.getItem(key).mapCatching { Json.decodeFromString(HealthParams.serializer(), it) }.getOrNull()
        }

}
