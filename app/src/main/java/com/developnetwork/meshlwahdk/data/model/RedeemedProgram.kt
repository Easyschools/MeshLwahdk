package com.developnetwork.meshlwahdk.data.model


import com.google.gson.annotations.SerializedName

data class RedeemedProgram(
    @SerializedName("barCode")
    val barCode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("product")
    val product: Product,
    @SerializedName("redemption_center")
    val redemptionCenter: RedemptionCenter,
    @SerializedName("created_at")
    val created_at: String
)