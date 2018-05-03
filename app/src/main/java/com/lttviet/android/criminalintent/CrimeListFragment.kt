package com.lttviet.android.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_crime_list.*

class CrimeListFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_crime_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        crime_recycler_view.layoutManager = LinearLayoutManager(activity)
        updateUI()
    }

    private fun updateUI() {
        val crimeLab: CrimeLab = CrimeLab.get(context!!)
        crime_recycler_view.adapter = CrimeListAdapter(crimeLab.crimes, context!!)
    }
}