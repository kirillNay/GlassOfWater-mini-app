package nay.kirill.healthcare.domain.useCases

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.daysUntil
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.ParamsWeekPage
import nay.kirill.healthcare.domain.dateToday
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository
import kotlin.random.Random

class GetAllParamsUseCase(
    private val repository: HealthParamsRepository
) {

    suspend operator fun invoke(weekNumber: Int = 0): ParamsWeekPage {
        val params = repository.getAllParams().sortedBy { it.date }
            .associateBy { it.date }

        val firstDate = params.values.minOf { it.date }

        val lastDate = params.values.maxOf { it.date }

        val totalDays = firstDate.daysUntil(lastDate)

        val firstDateOfWeek = lastDate
            .minus(lastDate.dayOfWeek.ordinal, DateTimeUnit.DAY)
            .minus(weekNumber, DateTimeUnit.WEEK)

        val resultParams = mutableListOf<HealthParams>()
        for (i in 0..6) {
            val date = firstDateOfWeek.plus(i, DateTimeUnit.DAY)

            if (date > lastDate) break

            val count = params[date]?.waterCount ?: 0
            resultParams.add(HealthParams(count, date))
        }

        return ParamsWeekPage(
            weekNumber = weekNumber,
            params = resultParams,
            total = totalDays
        )
    }

    private fun getMockData(): ParamsWeekPage  {
        val params = mutableListOf<HealthParams>()
        for (i in 0..6) {
            params.add(
                HealthParams(
                    waterCount = Random(123).nextInt(20),
                    date = dateToday
                        .minus(dateToday.dayOfWeek.ordinal, DateTimeUnit.DAY)
                        .plus(i, DateTimeUnit.DAY)
                )
            )
        }

        return ParamsWeekPage(
            weekNumber = 0,
            params = params,
            total = 7
        )
    }


}