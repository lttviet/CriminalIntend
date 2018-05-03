package com.lttviet.android.criminalintent

import android.support.v4.app.Fragment

class CrimeActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return CrimeFragment()
    }
}
