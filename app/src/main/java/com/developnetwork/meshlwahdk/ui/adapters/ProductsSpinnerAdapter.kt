package com.developnetwork.meshlwahdk.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.data.model.Product
import com.developnetwork.meshlwahdk.utils.extensions.setImageURL
import com.google.android.material.imageview.ShapeableImageView

class ProductsSpinnerAdapter(private val context: Context, val list: List<Product>) :
    BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context)
                .inflate(R.layout.selectable_item, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val item = list[position]

        holder.name.text = item.name
        if (!item.logo.isNullOrBlank())
            holder.logo.setImageURL(item.logo)

        return view
    }

    override fun getItem(position: Int): Product {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return list[position].id.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

//    override fun isEnabled(position: Int): Boolean {
//        return position != 0
//    }
//
//    override fun getDropDownView(
//        position: Int, convertView: View?,
//        parent: ViewGroup?
//    ): View? {
//        val view = super.getDropDownView(position, convertView, parent)
//        val tv = view as TextView
//        if (position == 0) { // Set the hint text color gray
//            tv.setTextColor(Color.GRAY)
//        } else {
//            tv.setTextColor(Color.BLACK)
//        }
//        return view
//    }

    private class ViewHolder(view: View) {
        val name: AppCompatTextView = view.findViewById(R.id.title)
        val logo: ShapeableImageView = view.findViewById(R.id.logo)
    }
}