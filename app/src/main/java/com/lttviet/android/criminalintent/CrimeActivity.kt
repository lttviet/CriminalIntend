package com.lttviet.android.criminalintent

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import java.util.*

class CrimeActivity : SingleFragmentActivity() {
    companion object {
        private const val EXTRA_CRIME_ID = "com.lttviet.android.criminalintent.crime_id"

        fun newIntent(context: Context?, crimeID: UUID): Intent {
            val intent = Intent(context, CrimeActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeID)
            return intent
        }
    }

    override fun createFragment(): Fragment {
        val crimeID: UUID = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID
        return CrimeFragment.newInstance(crimeID)
    }
}
