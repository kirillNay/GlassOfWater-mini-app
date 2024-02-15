package nay.kirill.glassOfWater.utils

import kotlinx.datetime.Instant

val Instant.onlyDate: String get() = toString().split("T")[0]