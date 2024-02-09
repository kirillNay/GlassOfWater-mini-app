package nay.kirill.glassOfWater.data

import android.content.SharedPreferences
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository

actual class HealthParamsRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : HealthParamsRepository {

    actual override suspend fun getParamByDate(date: String): Result<HealthParams> = runCatching {
        sharedPreferences
            .getString("$HEALTH_PARAMS_KEY:$date", null)
            ?.let { Json.decodeFromString<HealthParams>(it) }
            ?: HealthParams(0, date)
    }

    actual override suspend fun setParam(date: String, params: HealthParams) {
        sharedPreferences.edit()
            .putString("$HEALTH_PARAMS_KEY:$date", Json.encodeToString(params))
            .apply()
    }

    actual override suspend fun getAllParams(): List<HealthParams> = sharedPreferences.all
        .filter { it.key.contains(HEALTH_PARAMS_KEY) }
        .mapNotNull {
            try {
                Json.decodeFromString<HealthParams>(it.value as String)
            } catch (ignore: Throwable) {
                null
            }
        }

    companion object {

        private const val HEALTH_PARAMS_KEY = "HEALTH_PARAMS_KEY"

    }

}