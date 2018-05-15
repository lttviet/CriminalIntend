package com.lttviet.android.criminalintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_crime_pager.*
import java.util.*

class CrimePagerActivity: AppCompatActivity() {
    companion object {
        const val EXTRA_CRIME_ID: String = "com.lttviet.android.criminalintent.crime_id"

        fun newIntent(context: Context?, crimeID: UUID): Intent {
            val intent = Intent(context, CrimePagerActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeID)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime_pager)

        val crimeID: UUID = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID

        val crimes: List<Crime>? = CrimeLab.get(this)?.crimes

        crime_view_pager.adapter = object: FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val crime: Crime? = crimes?.get(position)
                return CrimeFragment.newInstance(crime?.id!!)
            }

            override fun getCount(): Int {
                return crimes?.size!!
            }
        }

        crime_view_pager.currentItem = crimes?.indexOfFirst { it.id == crimeID }!!

        crime_view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                first_button.isEnabled = crime_view_pager.currentItem != 0
                last_button.isEnabled = crime_view_pager.currentItem != crimes.size - 1
            }
        })

        first_button.setOnClickListener {
            crime_view_pager.currentItem = 0
        }

        last_button.setOnClickListener {
            crime_view_pager.currentItem = crimes.size - 1
        }
    }
}