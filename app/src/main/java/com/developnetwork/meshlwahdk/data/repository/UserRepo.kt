package com.ivestment.doctorna.data.repository

import com.ivestment.doctorna.data.model.addImage.AddImageDBResponse
import com.ivestment.doctorna.data.model.notification.NotificationDBResponse
import com.ivestment.doctorna.data.model.patientsProducts.PatientsProductsDBResponse
import com.ivestment.doctorna.data.model.token.TokenDBResponse
import com.ivestment.doctorna.data.network.Service
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Path
import java.io.File

interface UserRepo {
    suspend fun getUser(
        user_id: Int
    ): PatientsProductsDBResponse

    suspend fun sendToken(
        device_token: String
    ): TokenDBResponse

    suspend fun getNotifications(
        @Path("user_id") user_id: Int
    ): NotificationDBResponse

    suspend fun addImage(
        type: String?,
        imagePath: String?
    ): AddImageDBResponse
}

class UserRepoImpl(private val service: Service) : UserRepo {
    override suspend fun getUser(user_id: Int): PatientsProductsDBResponse {
        return service.getPatientsProducts(user_id)
    }

    override suspend fun sendToken(device_token: String): TokenDBResponse {
        return service.sendToken(device_token)
    }

    override suspend fun getNotifications(user_id: Int): NotificationDBResponse {
        return service.getPatientsNotifications(user_id)
    }

    override suspend fun addImage(type: String?, imagePath: String?): AddImageDBResponse {
        var filePart: MultipartBody.Part? = null

        if (!imagePath.isNullOrBlank()) {
            val file = File(imagePath)
            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            filePart =
                MultipartBody.Part.createFormData("basic_info[image]", file.name, requestFile)
        }


        return service.addImage(
            type?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            filePart
        )
    }
}