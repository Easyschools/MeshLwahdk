package com.developnetwork.meshlwahdk.utils


import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
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
fun openDatePicker(input: EditText, context: Context,future:Int=-1) {
    val c = Calendar.getInstance()
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
            input.setText(sdf.format(cale.time))
        },
        cyear,
        month,
        day
    )

    when(future){
        0->    dpd.datePicker.maxDate = c.timeInMillis
        1->    dpd.datePicker.minDate = c.timeInMillis
    }
    dpd.show()
}