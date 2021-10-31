package com.developnetwork.meshlwahdk.ui.main.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.data.model.RedeemedProgram
import com.google.android.material.textview.MaterialTextView

class HistoryAdapter :
    ListAdapter<RedeemedProgram, HistoryAdapter.ViewHolder>(RedeemedProgramItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(holder.absoluteAdapterPosition)

        holder.date.text = item.created_at

        item.product?.let {
            holder.name.text = it.name
        }

        item.redemptionCenter?.let {
            holder.pharmacy.text = it.name
        }
    }

    class RedeemedProgramItemDiffCallback : DiffUtil.ItemCallback<RedeemedProgram>() {
        override fun areItemsTheSame(
            oldItem: RedeemedProgram,
            newItem: RedeemedProgram
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RedeemedProgram,
            newItem: RedeemedProgram
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: MaterialTextView = view.findViewById(R.id.date)
        val name: MaterialTextView = view.findViewById(R.id.name)
        val pharmacy: MaterialTextView = view.findViewById(R.id.pharmacy)
    }
}