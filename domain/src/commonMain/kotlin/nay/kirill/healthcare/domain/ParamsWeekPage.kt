package nay.kirill.healthcare.domain

data class ParamsWeekPage(
    val weekNumber: Int,
    val params: List<HealthParams>,
    val total: Int
)