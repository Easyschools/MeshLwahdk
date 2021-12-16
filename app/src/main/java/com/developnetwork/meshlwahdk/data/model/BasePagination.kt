package com.developnetwork.meshlwahdk.data.model

import com.google.gson.annotations.SerializedName

data class BasePagination<D>(
    @SerializedName("data")
    val data: List<D>,
    @SerializedName("current_page")
    val currentPage: Int = 0,
    @SerializedName("last_page")
    val lastPage: Int = 0
)