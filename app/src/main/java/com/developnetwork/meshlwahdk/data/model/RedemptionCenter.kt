package com.developnetwork.meshlwahdk.data.model


import com.google.gson.annotations.SerializedName

data class RedemptionCenter(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("privileges")
    val privileges: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("user_id")
    val userId: Int
)