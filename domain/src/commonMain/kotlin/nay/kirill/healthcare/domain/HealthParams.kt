package nay.kirill.healthcare.domain

import kotlinx.serialization.Serializable

@Serializable
data class HealthParams(
    val waterCount: Int,
    val date: String
)