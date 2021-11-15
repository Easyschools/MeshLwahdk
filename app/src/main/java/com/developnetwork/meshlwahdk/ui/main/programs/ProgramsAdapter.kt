package com.developnetwork.meshlwahdk.ui.main.programs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.data.model.Program
import com.developnetwork.meshlwahdk.utils.extensions.setImageURL
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class ProgramsAdapter(private val click: (programID: Int,productID: Int) -> Unit) :
    ListAdapter<Program, ProgramsAdapter.ViewHolder>(ProgramItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.program_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(holder.absoluteAdapterPosition)
        item.product?.let {
            if (!it.logo.isNullOrBlank())
                holder.logo.setImageURL(it.logo)

            holder.title.text = it.name
        }

        holder.fields.text = item.fields

        holder.itemView.setOnClickListener {
            click(item.id,item.productId)
        }
    }

    class ProgramItemDiffCallback : DiffUtil.ItemCallback<Program>() {
        override fun areItemsTheSame(
            oldItem: Program,
            newItem: Program
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Program,
            newItem: Program
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val logo: ShapeableImageView = view.findViewById(R.id.logo)
        val title: MaterialTextView = view.findViewById(R.id.title)
        val fields: MaterialTextView = view.findViewById(R.id.fields)
    }
}