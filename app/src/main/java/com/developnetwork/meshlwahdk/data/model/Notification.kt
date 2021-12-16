package com.developnetwork.meshlwahdk.data.model


import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("receiver_id")
    val receiverId: Int,
    @SerializedName("seen")
    val seen: Int,
    @SerializedName("sender_id")
    val senderId: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)