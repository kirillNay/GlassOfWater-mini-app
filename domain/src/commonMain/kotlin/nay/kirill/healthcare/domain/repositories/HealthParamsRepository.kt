package nay.kirill.healthcare.domain.repositories

import nay.kirill.healthcare.domain.HealthParams

interface HealthParamsRepository {

    suspend fun getParamByDate(date: String): Result<HealthParams>

    suspend fun setParam(date: String, params: HealthParams)

    suspend fun getAllParams(): List<HealthParams>

    suspend fun clearParams()

}