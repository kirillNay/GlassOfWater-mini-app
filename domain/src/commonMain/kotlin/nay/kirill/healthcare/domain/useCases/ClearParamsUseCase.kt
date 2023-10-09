package nay.kirill.healthcare.domain.useCases

import nay.kirill.healthcare.domain.repositories.HealthParamsRepository

class ClearParamsUseCase(
    private val repository: HealthParamsRepository
) {

    suspend operator fun invoke() = repository.clearParams()

}