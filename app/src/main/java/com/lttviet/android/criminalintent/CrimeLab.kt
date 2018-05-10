package com.lttviet.android.criminalintent

import android.content.Context
import java.util.*

object CrimeLab {
    private var crimeLab: CrimeLab? = null
    private var idCrimeMap = linkedMapOf<UUID, Crime>()
    var crimes = listOf<Crime>()

    private operator fun invoke(context: Context?): CrimeLab? {
        genCrimes()
        crimeLab = this
        return crimeLab
    }

    fun get(context: Context?): CrimeLab? =
        crimeLab ?: CrimeLab(context)

    private fun genCrimes() {
        for (i in 0 until 100) {
            val c = Crime()
            c.title = String.format("Crime #%d", i)
            c.isSolved = (i % 2 == 0)
            idCrimeMap[c.id] = c
        }
        crimes = idCrimeMap.values.toList()
    }

    fun getCrime(id: UUID): Crime? = idCrimeMap[id]
    fun getIndex(id: UUID): Int = idCrimeMap.keys.indexOf(id)
}