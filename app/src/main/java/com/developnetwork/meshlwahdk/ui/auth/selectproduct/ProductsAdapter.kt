package com.developnetwork.meshlwahdk.ui.auth.selectproduct

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.data.model.Product
import com.developnetwork.meshlwahdk.utils.extensions.setImageURL
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class ProductsAdapter(
    private val list: List<Product>
) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    var selectedProductID = -1

    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.selectable_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[holder.absoluteAdapterPosition]
        holder.itemView.setOnClickListener {
            if (selectedProductID != item.id) {
                selectedProductID = item.id
                notifyItemChanged(position)
            }
        }

        if (selectedProductID == item.id) {
            holder.card.setStrokeColor(
                ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        context.resources,
                        R.color.colorPrimaryDark,
                        context.theme
                    )
                )
            )


        } else {
            holder.card.setStrokeColor(
                ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        context.resources,
                        android.R.color.transparent,
                        context.theme
                    )
                )
            )

        }

        holder.title.text = item.name

        if (!item.logo.isNullOrBlank())
            holder.logo.setImageURL(item.logo)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val logo: ShapeableImageView = view.findViewById(R.id.logo)
        val title: MaterialTextView = view.findViewById(R.id.title)
        val card: MaterialCardView = view.findViewById(R.id.parent)
    }
}