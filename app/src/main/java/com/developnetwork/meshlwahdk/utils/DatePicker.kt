package com.developnetwork.meshlwahdk.utils


import android.app.DatePickerDialog
import android.content.Context
import com.developnetwork.meshlwahdk.R
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*

fun openDatePicker(textview: MaterialTextView, context: Context) {
    val c = Calendar.getInstance()
    c.add(Calendar.DAY_OF_YEAR, 1)
    val cyear = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val dpd = DatePickerDialog(
        context,
        R.style.datepicker,
        { _, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            val cale = Calendar.getInstance()
            cale.set(year, monthOfYear, dayOfMonth)
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            textview.text = sdf.format(cale.time)
        },
        cyear,
        month,
        day
    )

    dpd.datePicker.maxDate = c.timeInMillis
    dpd.show()
}