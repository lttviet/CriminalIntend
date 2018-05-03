package com.lttviet.android.criminalintent

import android.content.Context
import java.util.*

object CrimeLab {
    private var crimeLab: CrimeLab? = null
    private var crimes = arrayListOf<Crime>()

    private operator fun invoke(context: Context): CrimeLab {
        genCrimes()
        crimeLab = this
        return crimeLab!!
    }

    fun get(context: Context): CrimeLab =
        crimeLab ?: CrimeLab(context)

    private fun genCrimes() {
        for (i in 0 until 100) {
            val c = Crime()
            c.title = String.format("Crime #%d", i)
            c.isSolved = (i % 2 == 0)
            crimes.add(c)
        }
    }


    fun getCrime(id: UUID): Crime? =
            crimes.find { it.id == id }

}