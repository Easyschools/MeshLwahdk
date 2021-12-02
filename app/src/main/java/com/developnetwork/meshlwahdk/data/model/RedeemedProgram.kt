package com.developnetwork.meshlwahdk.data.model


import com.google.gson.annotations.SerializedName

data class RedeemedProgram(
    @SerializedName("barCode")
    val barCode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("program")
    val program: Program,
    @SerializedName("redemption_center")
    val redemptionCenter: RedemptionCenter,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: String
)