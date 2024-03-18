package nay.kirill.healthcare.domain

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class HealthParams(
    val waterCount: Int,
    val date: LocalDate
)