package com.ivestment.doctorna.data.model

import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("id")
    val id: Int=0,
    @SerializedName("name")
    val name: String=""
)