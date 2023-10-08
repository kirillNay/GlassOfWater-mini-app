package nay.kirill.healthcare.domain.useCases

import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository

class GetAllParamsUseCase(
    private val repository: HealthParamsRepository
) {

    suspend operator fun invoke(): List<HealthParams> = repository.getAllParams()

}