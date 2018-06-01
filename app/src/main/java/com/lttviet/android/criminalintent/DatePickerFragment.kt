package com.lttviet.android.criminalintent

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import java.util.*

class DatePickerFragment: DialogFragment() {
    companion object {
        const val ARG_DATE = "date"
        const val EXTRA_DATE = "com.lttviet.android.criminalintent.date"

        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)

            val fragment = DatePickerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private fun sendResult(resultCode: Int, date: Date) {
        if (targetFragment == null) {
            return
        }

        val intent = Intent()
        intent.putExtra(EXTRA_DATE, date)

        targetFragment?.onActivityResult(targetRequestCode, resultCode, intent)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date: Date = arguments?.getSerializable(ARG_DATE) as Date

        val calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        val v: View = LayoutInflater.from(context).inflate(R.layout.dialog_date, null)

        val datePicker = v.findViewById<DatePicker>(R.id.dialog_date_picker)
        datePicker.init(year, month, day, null)

        return AlertDialog.Builder(context!!)
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    val d: Date = GregorianCalendar(datePicker.year,
                            datePicker.month,
                            datePicker.dayOfMonth).time
                    sendResult(Activity.RESULT_OK, d)
                })
                .create()
    }
}