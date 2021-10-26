package com.developnetwork.meshlwahdk.data.model

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("product_id")
    @Expose
    var productId: String? = null,

    @SerializedName(value="displayName", alternate=["name"])
    @Expose
    var name: String? = null,

    @SerializedName("phone")
    @Expose
    var phone: String? = null,

    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("region_id")
    @Expose
    var regionId: String? = null,

    @SerializedName("subRegion_id")
    @Expose
    var subRegionId: String? = null,

    @SerializedName("gender")
    @Expose
    var gender: String? = null,

    @SerializedName("national_id")
    @Expose
    var nationalId: String? = null,

    @SerializedName("Age")
    @Expose
    var age: String? = null,

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null,

    @SerializedName("phone_verified_at")
    @Expose
    var phoneVerifiedAt: String? = null,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,

    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("identity_card")
    @Expose
    var identity_card: String? = null,

    @SerializedName("insurance_card")
    @Expose
    var insurance_card: String? = null,
    @SerializedName("token")
    val token: String = "",
    @SerializedName("img")
    val img: String = ""
) {
    fun serialize(): String {
        // Serialize this class into a JSON string using GSON
        val gson = Gson()
        return gson.toJson(this)
    }

    fun create(serializedData: String): User {
        // Use GSON to instantiate this class using the JSON representation of the state
        val gson = Gson()
        return gson.fromJson(serializedData, User::class.java)
    }
}