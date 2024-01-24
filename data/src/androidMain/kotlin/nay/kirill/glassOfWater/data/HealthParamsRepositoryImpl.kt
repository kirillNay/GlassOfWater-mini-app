package nay.kirill.glassOfWater.data

import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository

actual class HealthParamsRepositoryImpl : HealthParamsRepository {

    actual override suspend fun getParamByDate(date: String): Result<HealthParams> {
        TODO("Not yet implemented")
    }

    actual override suspend fun setParam(date: String, params: HealthParams) {
    }

    actual override suspend fun getAllParams(): List<HealthParams> {
        TODO("Not yet implemented")
    }

    actual override suspend fun clearParams() {
    }

}