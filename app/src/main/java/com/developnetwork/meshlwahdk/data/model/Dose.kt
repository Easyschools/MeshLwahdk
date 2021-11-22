package com.developnetwork.meshlwahdk.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Dose(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("frequency")
    val frequency: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("product")
    val product: @RawValue Product
) : Parcelable