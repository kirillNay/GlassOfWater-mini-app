package nay.kirill.healthcare.domain

import kotlinx.datetime.Clock

val dateToday get() = Clock.System.now().toString().split("T")[0]