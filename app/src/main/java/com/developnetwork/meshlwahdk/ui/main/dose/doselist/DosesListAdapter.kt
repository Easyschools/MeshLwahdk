package com.developnetwork.meshlwahdk.ui.main.dose.doselist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.data.model.Dose
import com.developnetwork.meshlwahdk.utils.extensions.setImageURL
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class DosesListAdapter(private val click: (dose: Dose) -> Unit) :
    ListAdapter<Dose, DosesListAdapter.ViewHolder>(DoseItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.dose_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(holder.absoluteAdapterPosition)

        if (!item.product.logo.isNullOrBlank())
            holder.logo.setImageURL(item.product.logo)

        holder.name.text = item.product.name
        holder.frequency.text = item.frequency.toString()
        holder.duration.text = item.duration

        holder.itemView.setOnClickListener {
            click(item)
        }

    }

    class DoseItemDiffCallback : DiffUtil.ItemCallback<Dose>() {
        override fun areItemsTheSame(
            oldItem: Dose,
            newItem: Dose
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Dose,
            newItem: Dose
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val logo: ShapeableImageView = view.findViewById(R.id.productLogo)
        val name: MaterialTextView = view.findViewById(R.id.productName)
        val frequency: MaterialTextView = view.findViewById(R.id.frequency)
        val duration: MaterialTextView = view.findViewById(R.id.duration)
    }
}