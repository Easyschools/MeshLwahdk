package com.ivestment.doctorna.data.model


import com.google.gson.annotations.SerializedName

data class InsuranceCard(
    @SerializedName("active")
    val active: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("max_used")
    val maxUsed: Int?=null,
    @SerializedName("name")
    val name: String,
    @SerializedName("patient_id")
    val patientId: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)