package com.ivestment.doctorna.data.model
import com.google.gson.annotations.SerializedName

data class BaseResponse<D>(
    @SerializedName( "message")
    var message: String? = null,
    @SerializedName( "data")
    var data: D,
    @SerializedName("success")
    var isSuccess: Boolean = false,
    @SerializedName( "status")
    var status: Int = 0
)