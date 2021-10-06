package com.ivestment.doctorna.data.model


import com.google.gson.annotations.SerializedName

data class PatientCategory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String
)