package com.developnetwork.meshlwahdk.data.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("company_id")
    val companyId: Int,
    @SerializedName("created_at")
    val createdAt: Any,
    @SerializedName("description")
    val description: String,
    @SerializedName("has_mobile_app")
    val hasMobileApp: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("teamleader_id")
    val teamleaderId: Int,
    @SerializedName("default_dose")
    val defaultDose: Int,
    @SerializedName("default_frequency")
    val defaultFrequency: Int
)