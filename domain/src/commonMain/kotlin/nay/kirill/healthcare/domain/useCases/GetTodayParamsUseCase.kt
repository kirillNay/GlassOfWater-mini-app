package nay.kirill.healthcare.domain.useCases

import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.dateToday
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository

class GetTodayParamsUseCase(
    private val repository: HealthParamsRepository
) {

    suspend operator fun invoke(): HealthParams = repository.getParamByDate(dateToday).getOrDefault(HealthParams(0, dateToday))

}