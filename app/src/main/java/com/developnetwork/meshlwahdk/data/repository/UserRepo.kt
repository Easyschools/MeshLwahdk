package com.developnetwork.meshlwahdk.data.repository

import com.developnetwork.meshlwahdk.data.model.User
import com.developnetwork.meshlwahdk.data.network.Service
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

interface UserRepo {
    suspend fun editProfile(
        name: String,
        gender: String,
        region_id: Int,
        subRegion_id: Int,
        age: String,
        profilePicPath: String?
    ): User

    suspend fun getUser(): User

    suspend fun updatePhoneNumber(phone: String): Any

}

class UserRepoImpl(
    private val service: Service
) : UserRepo {
    override suspend fun getUser(): User {
        return service.getPatient().data
    }

    override suspend fun editProfile(
        name: String,
        gender: String,
        region_id: Int,
        subRegion_id: Int,
        age: String,
        profilePicPath: String?
    ): User {
        var profilePicFilePart: MultipartBody.Part? = null

        if (!profilePicPath.isNullOrBlank()) {
            val file = File(profilePicPath)
            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            profilePicFilePart =
                MultipartBody.Part.createFormData("profile_photo", file.name, requestFile)
        }


        return service.editProfile(
            name.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            gender.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            "1".toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            region_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            subRegion_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            age.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            profilePicFilePart
        ).data
    }

    override suspend fun updatePhoneNumber(phone: String): Any {
        return service.updatePhoneNumber(phone).data
    }

}