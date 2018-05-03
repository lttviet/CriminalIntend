package com.lttviet.android.criminalintent

import java.util.*

class Crime {
    val id: UUID = UUID.randomUUID()
    val date = Date()
    var title: String = ""
    var isSolved: Boolean = false
}