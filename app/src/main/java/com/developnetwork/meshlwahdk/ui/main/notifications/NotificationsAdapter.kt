package com.developnetwork.meshlwahdk.ui.main.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.data.model.Notification
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView


//class NotificationsAdapter(private val click: (data: NotificationData) -> Unit) :
class NotificationsAdapter() :
    ListAdapter<Notification, NotificationsAdapter.ViewHolder>(NotificationItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.notification_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = getItem(position)

            holder.msg.text =notification.message

        holder.date.text = notification.date

//        holder.itemView.setOnClickListener {
//            click(notification.notificationData)
//        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val card: MaterialCardView = view.findViewById(R.id.parent)
        val msg: MaterialTextView = view.findViewById(R.id.title)
        val date: MaterialTextView = view.findViewById(R.id.time)
    }

    class NotificationItemDiffCallback : DiffUtil.ItemCallback<Notification>() {
        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem == newItem
        }
    }
}