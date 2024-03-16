package nay.kirill.healthcare.domain.useCases

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.daysUntil
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import nay.kirill.healthcare.domain.HealthParams
import nay.kirill.healthcare.domain.ParamsWeekPage
import nay.kirill.healthcare.domain.dateToday
import nay.kirill.healthcare.domain.repositories.HealthParamsRepository

class GetAllParamsUseCase(
    private val repository: HealthParamsRepository
) {

    suspend operator fun invoke(weekNumber: Int = 0): ParamsWeekPage {
        val params = repository.getAllParams().sortedBy { it.date }
            .associateBy { it.date }

        val firstDate = params.values.minOf { it.date }

        val lastDate = dateToday.plus( 6 - dateToday.dayOfWeek.ordinal, DateTimeUnit.DAY)

        val totalWeeks = firstDate.daysUntil(lastDate) / 7

        val firstDateOfWeek = lastDate
            .minus(6, DateTimeUnit.DAY)
            .minus(weekNumber, DateTimeUnit.WEEK)

        val resultParams = mutableListOf<HealthParams>()
        for (i in 0..6) {
            val date = firstDateOfWeek.plus(i, DateTimeUnit.DAY)

            val count = params[date]?.waterCount ?: 0
            resultParams.add(HealthParams(count, date))
        }

        return ParamsWeekPage(
            weekNumber = weekNumber,
            params = resultParams,
            totalWeeks = totalWeeks
        )
    }

}