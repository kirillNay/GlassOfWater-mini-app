package nay.kirill.healthcare.domain.useCases

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository
import kotlin.random.Random

class MockParamsUseCase(
    private val repository: HealthParamsRepository,
    private val clearParamsUseCase: ClearParamsUseCase
) {

    suspend operator fun invoke() {
        clearParamsUseCase()
        for (i in 0..5) {
            val date = Clock.System.now().minus(i, DateTimeUnit.DAY, TimeZone.UTC).toString().split("T")[0]
            val count = Random(i).nextInt(0, 10)

            repository.setParam(date, HealthParams(count, date))
        }

    }

}