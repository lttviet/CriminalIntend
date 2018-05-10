package com.lttviet.android.criminalintent

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_crime.view.*

class CrimeListAdapter(private var crimes: List<Crime>?,
                       private val itemClick: (Crime) -> Unit):
        RecyclerView.Adapter<CrimeListAdapter.CrimeHolder>() {

    override fun getItemCount(): Int {
        return crimes?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_crime, parent, false)
        return CrimeHolder(view)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        crimes?.get(position)?.let { crime ->
            holder.bind(crime, itemClick)
        }
    }

    class CrimeHolder(itemView: View):
            RecyclerView.ViewHolder(itemView), ViewHolder {

        override fun bind(crime: Crime, itemClick: (Crime) -> Unit) {
            itemView.crime_title.text = crime.title
            itemView.crime_date.text = android.text.format.DateFormat
                    .format("EEEE, MMM d, yyyy", crime.date)
            itemView.crime_solved.visibility = if (crime.isSolved) View.VISIBLE else View.INVISIBLE
            itemView.setOnClickListener { itemClick(crime) }
        }
    }
}

interface ViewHolder {
    fun bind(crime: Crime, itemClick: (Crime) -> Unit)
}

