package com.lttviet.android.criminalintent

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item_crime.view.*

class CrimeListAdapter(private val crimes: List<Crime>, private val context: Context): RecyclerView.Adapter<CrimeHolder>() {
    override fun getItemCount(): Int {
        return crimes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        return CrimeHolder(LayoutInflater.from(context).inflate(R.layout.list_item_crime, parent, false))
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        holder.crimeTitle.text = crimes[position].title
        holder.crimeDate.text = crimes[position].date.toString()
    }
}

class CrimeHolder(view: View): RecyclerView.ViewHolder(view) {
    val crimeTitle: TextView = view.crime_title
    val crimeDate: TextView = view.crime_date
}