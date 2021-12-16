package com.developnetwork.meshlwahdk.data.repository

import com.developnetwork.meshlwahdk.BuildConfig
import com.developnetwork.meshlwahdk.data.model.User
import com.developnetwork.meshlwahdk.data.network.Service
import com.ivestment.doctorna.data.model.BaseResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

interface AuthRepo {
    suspend fun login(
        email: String,
        password: String,
        notificationToken: String?
    ): User

    suspend fun checkPhone(phone: String): BaseResponse<Boolean>
    suspend fun getPhoneUser(phone: String): User
    suspend fun registerPhone(phone: String): User
    suspend fun resendCode(phone: String): Any
    suspend fun confirmPhone(code: String, phone: String): String

    suspend fun completeRegister(
        name: String,
        phone: String,
        email: String?,
        password: String,
        nationalId: String,
        gender: String,
        region_id: Int,
        subRegion_id: Int?,
        company_id: Int,
        age: String,
        health_insurance: String,
        categoryID: Int,
        profilePicImagePath: String?,
        categoryImagePath: String?,
        identityCardImagePath: String?,
        insuranceCardImagePath: String?,
        notificationToken: String?
    ): User

    suspend fun addProductID( product_id: Int): Any

    suspend fun forgetPassword(phone: String): Any

    suspend fun confirmForgotPasswordPhone(code: String, phone: String): String

    suspend fun resetPassword(
        phone: String,
        newPassword: String,
        confirmPassword: String
    ): Any

}

class AuthRepoImpl(private val service: Service) : AuthRepo {
    override suspend fun login(
        email: String, password: String,
        notificationToken: String?
    ): User {
        return service.userLogin(email, password, BuildConfig.company_id, notificationToken).data
    }

    override suspend fun checkPhone(phone: String): BaseResponse<Boolean> {
        return service.checkPhone(phone, BuildConfig.company_id)
    }

    override suspend fun getPhoneUser(phone: String): User {
        return service.getPhoneUser(phone, BuildConfig.company_id).data
    }

    override suspend fun resendCode(phone: String): Any {
        return service.resendCode(phone, BuildConfig.company_id).data
    }

    override suspend fun registerPhone(phone: String): User {
        return service.phoneRegister(phone, BuildConfig.company_id).data
    }

    override suspend fun confirmPhone(code: String, phone: String): String {
        return service.confirmPhone(phone,  BuildConfig.company_id,code).data
    }

    override suspend fun completeRegister(
        name: String,
        phone: String,
        email: String?,
        password: String,
        nationalId: String,
        gender: String,
        region_id: Int,
        subRegion_id: Int?,
        company_id: Int,
        age: String,
        health_insurance: String,
        categoryID: Int,
        profilePicImagePath: String?,
        categoryImagePath: String?,
        identityCardImagePath: String?,
        insuranceCardImagePath: String?,
        notificationToken: String?
    ): User {
        var profilePicFilePart: MultipartBody.Part? = null
        var categoryFilePart: MultipartBody.Part? = null
        var identityFilePart: MultipartBody.Part? = null
        var insuranceFilePart: MultipartBody.Part? = null

        if (!profilePicImagePath.isNullOrBlank()) {
            val file = File(profilePicImagePath)
            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            profilePicFilePart =
                MultipartBody.Part.createFormData("profile_photo", file.name, requestFile)
        }

        if (!categoryImagePath.isNullOrBlank()) {
            val file = File(categoryImagePath)
            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            categoryFilePart =
                MultipartBody.Part.createFormData("document", file.name, requestFile)
        }

        if (!identityCardImagePath.isNullOrBlank()) {
            val file = File(identityCardImagePath)
            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            identityFilePart =
                MultipartBody.Part.createFormData("identity_card", file.name, requestFile)
        }

        if (!insuranceCardImagePath.isNullOrBlank()) {
            val file = File(insuranceCardImagePath)
            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            insuranceFilePart =
                MultipartBody.Part.createFormData("insurance_card", file.name, requestFile)
        }

        val subregionID=if(subRegion_id!! >0)
            subRegion_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        else
            null

        return service.completeRegister(
            name.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            phone.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            email?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            password.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            nationalId.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            gender.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            region_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            subregionID,
            company_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            age.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            health_insurance.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            categoryID.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            profilePicFilePart,
            categoryFilePart,
            identityFilePart,
            insuranceFilePart,
            notificationToken.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        ).data
    }

    override suspend fun addProductID(product_id: Int): Any {
        return service.addProductID(product_id).data
    }

    override suspend fun forgetPassword(phone: String): Any {
        return service.forgetPassword(phone, BuildConfig.company_id).data
    }

    override suspend fun confirmForgotPasswordPhone(code: String, phone: String): String {
        return service.confirmForgotPasswordPhone(phone, BuildConfig.company_id, code).data
    }

    override suspend fun resetPassword(
        phone: String,
        newPassword: String,
        confirmPassword: String
    ): Any {
        return service.resetPassword(phone, newPassword, confirmPassword).data
    }

}