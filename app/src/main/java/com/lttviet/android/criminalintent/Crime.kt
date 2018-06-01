package com.lttviet.android.criminalintent

import java.util.*

data class Crime(
        val id: UUID = UUID.randomUUID(),
        var date: Calendar = Calendar.getInstance(),
        var title: String = "",
        var isSolved: Boolean = false
)