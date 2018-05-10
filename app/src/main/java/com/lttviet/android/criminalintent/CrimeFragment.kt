package com.lttviet.android.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_crime.*
import java.util.*

class CrimeFragment: Fragment() {
    companion object {
        const val ARG_CRIME_ID = "crime_id"

        fun newInstance(crimeID: UUID): CrimeFragment {
            val args = Bundle()
            args.putSerializable(ARG_CRIME_ID, crimeID)

            val fragment = CrimeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var crime: Crime? = Crime()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val crimeID: UUID = arguments?.get(ARG_CRIME_ID) as UUID
        crime = CrimeLab.get(activity)?.getCrime(crimeID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_crime, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        crime_title.setText(crime?.title)
        crime_title.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime?.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        crime_date.text = crime?.date.toString()
        crime_date.isEnabled = false

        crime_solved.isChecked = crime?.isSolved!!
        crime_solved.setOnCheckedChangeListener {
            _, isChecked ->  crime?.isSolved = isChecked
        }
    }
}