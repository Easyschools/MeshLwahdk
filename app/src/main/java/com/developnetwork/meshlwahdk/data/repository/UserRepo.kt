package com.developnetwork.meshlwahdk.data.repository

import com.developnetwork.meshlwahdk.data.network.Service

interface UserRepo {
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

class UserRepoImpl(private val service: Service) : UserRepo {
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