package nay.kirill.glassOfWater.data

import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository

expect class HealthParamsRepositoryImpl : HealthParamsRepository {

    override suspend fun getParamByDate(date: String): Result<HealthParams>

    override suspend fun setParam(date: String, params: HealthParams)

    override suspend fun getAllParams(): List<HealthParams>

    override suspend fun clearParams()

}