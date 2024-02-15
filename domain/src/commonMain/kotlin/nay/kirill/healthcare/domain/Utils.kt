package nay.kirill.healthcare.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val dateToday get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date