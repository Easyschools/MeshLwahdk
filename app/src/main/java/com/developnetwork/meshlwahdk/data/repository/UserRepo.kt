package com.developnetwork.meshlwahdk.data.repository

import com.developnetwork.meshlwahdk.data.model.User
import com.developnetwork.meshlwahdk.data.network.Service
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
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
//    suspend fun getUser(
//        user_id: Int
//    ): PatientsProductsDBResponse
//
//    suspend fun sendToken(
//        device_token: String
//    ): TokenDBResponse
//
//    suspend fun getNotifications(
//        @Path("user_id") user_id: Int
//    ): NotificationDBResponse
//
//    suspend fun addImage(
//        type: String?,
//        imagePath: String?
//    ): AddImageDBResponse
}

class UserRepoImpl(
    private val service: Service,
    private val sharedPreferencesManager: SharedPreferencesManager
) : UserRepo {
    override suspend fun getUser(): User {
        return service.getPhoneUser(sharedPreferencesManager.userPhone).data
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

    //    override suspend fun getUser(user_id: Int): PatientsProductsDBResponse {
//        return service.getPatientsProducts(user_id)
//    }
//
//    override suspend fun sendToken(device_token: String): TokenDBResponse {
//        return service.sendToken(device_token)
//    }
//
//    override suspend fun getNotifications(user_id: Int): NotificationDBResponse {
//        return service.getPatientsNotifications(user_id)
//    }
//
//    override suspend fun addImage(type: String?, imagePath: String?): AddImageDBResponse {
//        var filePart: MultipartBody.Part? = null
//
//        if (!imagePath.isNullOrBlank()) {
//            val file = File(imagePath)
//            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//            filePart =
//                MultipartBody.Part.createFormData("basic_info[image]", file.name, requestFile)
//        }
//
//
//        return service.addImage(
//            type?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            filePart
//        )
//    }
}