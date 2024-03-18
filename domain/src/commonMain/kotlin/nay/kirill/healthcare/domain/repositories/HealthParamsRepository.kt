package nay.kirill.healthcare.domain.repositories

import kotlinx.datetime.LocalDate
import nay.kirill.healthcare.domain.HealthParams

interface HealthParamsRepository {

    suspend fun getParamByDate(date: LocalDate): Result<HealthParams>

    suspend fun setParam(date: LocalDate, params: HealthParams)

    suspend fun getAllParams(): List<HealthParams>

}