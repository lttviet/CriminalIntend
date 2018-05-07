package com.lttviet.android.criminalintent

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_crime.view.*
import org.jetbrains.anko.toast

class CrimeListAdapter(var crimes: List<Crime>?): RecyclerView.Adapter<CrimeHolder>() {
    override fun getItemCount(): Int {
        return crimes?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_crime, parent, false)
        return CrimeHolder(textView)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        crimes?.get(position)?.let { crime ->
            holder.bind(crime)
        }
    }
}

class CrimeHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
    fun bind(crime: Crime) {
        itemView.crime_title.text = crime.title
        itemView.crime_date.text = crime.date.toString()
        itemView.setOnClickListener { _ ->
            itemView.context.toast(crime.title + " clicked!")
        }
    }
}