package com.developnetwork.meshlwahdk.data.model


import com.google.gson.annotations.SerializedName

data class Program(
    @SerializedName("fields")
    val fields: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("product")
    val product: Product?
)