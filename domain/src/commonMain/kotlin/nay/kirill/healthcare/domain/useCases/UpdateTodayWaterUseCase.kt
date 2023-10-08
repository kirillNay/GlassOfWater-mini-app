package nay.kirill.healthcare.domain.useCases

import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.dateToday
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository

class UpdateTodayWaterUseCase(
    private val repository: HealthParamsRepository
) {

    suspend operator fun invoke(count: Int) {
        repository.setParam(dateToday, HealthParams(count, dateToday))
    }

}