package com.lttviet.android.criminalintent

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.TimePicker
import java.util.*

class TimePickerFragment: DialogFragment() {
    companion object {
        const val ARG_TIME = "time"
        const val EXTRA_TIME = "com.lttviet.android.criminalintent.time"

        fun newInstance(calendar: Calendar): TimePickerFragment {
            val args = Bundle()
            args.putSerializable(ARG_TIME, calendar)

            val fragment = TimePickerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private fun sendResult(resultCode: Int, calendar: Calendar) {
        if (targetFragment == null) {
            return
        }

        val intent = Intent()
        intent.putExtra(EXTRA_TIME, calendar)

        targetFragment?.onActivityResult(targetRequestCode, resultCode, intent)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar: Calendar = arguments?.getSerializable(ARG_TIME) as Calendar

        val v: View = LayoutInflater.from(context).inflate(R.layout.dialog_time, null)

        val timePicker = v.findViewById<TimePicker>(R.id.dialog_time_picker)
        timePicker.hour = calendar.get(Calendar.HOUR_OF_DAY)
        timePicker.minute = calendar.get(Calendar.MINUTE)

        return AlertDialog.Builder(context!!)
                .setView(v)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    val c: Calendar = calendar
                    c.set(Calendar.HOUR_OF_DAY, timePicker.hour)
                    c.set(Calendar.MINUTE, timePicker.minute)
                    sendResult(Activity.RESULT_OK, c)
                })
                .create()
    }
}