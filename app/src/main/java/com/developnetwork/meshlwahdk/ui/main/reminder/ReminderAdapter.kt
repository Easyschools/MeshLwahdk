package com.developnetwork.meshlwahdk.ui.main.reminder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.data.model.Reminder
import com.developnetwork.meshlwahdk.utils.extensions.setImageURL
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class ReminderAdapter(private val list: List<Reminder>) :
    RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.reminder_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[holder.absoluteAdapterPosition]

        holder.time.text = "${holder.itemView.context.getString(R.string.your_next_dose)} ${item.time}"
        holder.name.text = item.productName
        if (!item.productLogo.isNullOrBlank())
            holder.logo.setImageURL(item.productLogo)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val logo: ShapeableImageView = view.findViewById(R.id.logo)
        val name: MaterialTextView = view.findViewById(R.id.name)
        val time: MaterialTextView = view.findViewById(R.id.time)
    }
}