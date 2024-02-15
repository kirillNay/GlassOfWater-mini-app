package nay.kirill.glassOfWater.data

import kotlinx.datetime.LocalDate
import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository

expect class HealthParamsRepositoryImpl : HealthParamsRepository {

    override suspend fun getParamByDate(date: LocalDate): Result<HealthParams>

    override suspend fun setParam(date: LocalDate, params: HealthParams)

    override suspend fun getAllParams(): List<HealthParams>

}