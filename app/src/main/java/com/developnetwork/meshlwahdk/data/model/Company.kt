package com.developnetwork.meshlwahdk.data.model


import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("commercial")
    val commercial: String,
    @SerializedName("created_at")
    val createdAt: Any,
    @SerializedName("has_mobile_app")
    val hasMobileApp: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("tax")
    val tax: String,
    @SerializedName("updated_at")
    val updatedAt: Any,
    @SerializedName("user_id")
    val userId: Int
//    @SerializedName("user")
//    val user: User
)