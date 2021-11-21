package com.developnetwork.meshlwahdk.utils.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.constraintlayout.widget.ConstraintLayout
import com.developnetwork.meshlwahdk.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

//import kotlinx.android.synthetic.main.meshlwahdk_spinner.view.*

class MeshLwahdkSpinner(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    constructor(context: Context) : this(context, null)


    init {
        inflate(context, R.layout.meshlwahdk_spinner, this)
//        setBackgroundColor(android.R.color.transparent)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.meshlWahdkSpinner)

        val hint = findViewById<MaterialTextView>(R.id.hint)
        val indicator = findViewById<ShapeableImageView>(R.id.indicator)

        hint.text = attributes.getString(R.styleable.meshlWahdkSpinner_hint)

        indicator.setOnClickListener {
            getSpinner().performClick()
        }
        attributes.recycle()
    }

    fun setAdapter(arrayAdapter: BaseAdapter) {
        getSpinner().adapter = arrayAdapter
    }

    fun getSpinner() = findViewById<AppCompatSpinner>(R.id.spinner)
    fun getSelectedItem() = getSpinner().selectedItem
    fun getSelectedItemId() = getSpinner().selectedItemId.toInt()
    fun onItemSelected(click: (id: Int) -> Unit) {
        getSpinner().onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                getSpinner().setSelection(i)
        }
    }
}