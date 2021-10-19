package com.developnetwork.meshlwahdk.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.ivestment.doctorna.data.model.Region

class RegionsAdapter(private val context: Context, val list: List<Region>) :
    BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context)
                .inflate(android.R.layout.simple_spinner_item, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val region = list[position]

        holder.name.text = region.name
        return view
    }

    override fun getItem(position: Int): Region {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return list[position].id.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
    override fun isEnabled(position: Int): Boolean {
        return position != 0
    }

    override fun getDropDownView(
        position: Int, convertView: View?,
        parent: ViewGroup?
    ): View? {
        val view = super.getDropDownView(position, convertView, parent)
        val tv = view as TextView
        if (position == 0) { // Set the hint text color gray
            tv.setTextColor(Color.GRAY)
        } else {
            tv.setTextColor(Color.BLACK)
        }
        return view
    }

    private class ViewHolder(view: View) {
        val name: AppCompatTextView = view.findViewById(android.R.id.text1)
    }
}