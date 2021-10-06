package com.developnetwork.meshlwahdk.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.BaseAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import com.developnetwork.meshlwahdk.R
import kotlinx.android.synthetic.main.meshlwahdk_spinner.view.*

class MeshLwahdkSpinner (context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    constructor(context: Context) : this(context, null)
    init {
        inflate(context, R.layout.meshlwahdk_spinner, this)
//        setBackgroundColor(android.R.color.transparent)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.meshlWahdkSpinner)

        hint.text = attributes.getString(R.styleable.meshlWahdkSpinner_hint)

        indicator.setOnClickListener {
            spinner.performClick()
        }
        attributes.recycle()
    }

    fun setAdapter(arrayAdapter: BaseAdapter) {
        spinner.adapter = arrayAdapter
    }

    fun getSpinner() = spinner
    fun getSelectedItem() = spinner.selectedItem
    fun getSelectedItemId() = spinner.selectedItemId.toInt()
    fun onItemSelected(click: (id: Int) -> Unit) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // not needed
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, id: Long) {
                click(id.toInt())
            }
        }
    }

    fun <T> selectItem(list: List<T>, item: T) {
        for (i in list.indices) {
            if (item == list[i])
                spinner.setSelection(i)
        }
    }
}