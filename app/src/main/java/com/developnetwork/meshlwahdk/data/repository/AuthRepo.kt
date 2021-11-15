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

//    suspend fun createControllerPatient(): CreateControllerPatientDBResponse

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
        subRegion_id: Int,
        company_id: Int,
        product_id: Int,
        age: String,
        health_insurance: String,
        categoryID: Int,
        profilePicImagePath: String?,
        categoryImagePath: String?,
        identityCardImagePath: String?,
        insuranceCardImagePath: String?,
        notificationToken: String?
    ): User

//    suspend fun userRegister(
//        name: String,
//        phone: String,
//        email: String,
//        password: String,
//        nationalId: String,
//        gender: String,
//        region_id: Int,
//        subRegion_id: Int,
//        subSubRegion_id: Int,
//        company_id: String?,
//        product_id: String?,
//        address: String,
//        health_insurance: String,
//        identityCardImagePath: String?,
//        insuranceCardImagePath: String?,
//        raysCardImagePath: String?,
//        analyseCardImagePath: String?,
//        lang: String
//    ): RegisterDBResponse

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

//    override suspend fun createControllerPatient(): CreateControllerPatientDBResponse {
//        return service.createControllerPatient()
//    }

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
        subRegion_id: Int,
        company_id: Int,
        product_id: Int,
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

        return service.completeRegister(
            name.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            phone.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            email?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            password.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            nationalId.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            gender.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            region_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            subRegion_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            "1".toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            company_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            product_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
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

//    override suspend fun userRegister(
//        name: String,
//        phone: String,
//        email: String,
//        password: String,
//        nationalId: String,
//        gender: String,
//        region_id: Int,
//        subRegion_id: Int,
//        subSubRegion_id: Int,
//        company_id: String?,
//        product_id: String?,
//        address: String,
//        health_insurance: String,
//        identityCardImagePath: String?,
//        insuranceCardImagePath: String?,
//        raysCardImagePath: String?,
//        analyseCardImagePath: String?,
//        lang: String
//    ): RegisterDBResponse {
//        var identityFilePart: MultipartBody.Part? = null
//        var insuranceFilePart: MultipartBody.Part? = null
//        var raysFilePart: MultipartBody.Part? = null
//        var analyseFilePart: MultipartBody.Part? = null
//
//        if (!identityCardImagePath.isNullOrBlank()) {
//            val file = File(identityCardImagePath)
//            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//            identityFilePart =
//                MultipartBody.Part.createFormData("identityCard", file.name, requestFile)
//        }
//
//        if (!insuranceCardImagePath.isNullOrBlank()) {
//            val file = File(insuranceCardImagePath)
//            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//            insuranceFilePart =
//                MultipartBody.Part.createFormData("insuranceCard", file.name, requestFile)
//        }
//
//        if (!raysCardImagePath.isNullOrBlank()) {
//            val file = File(raysCardImagePath)
//            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//            raysFilePart =
//                MultipartBody.Part.createFormData("rays", file.name, requestFile)
//        }
//
//        if (!analyseCardImagePath.isNullOrBlank()) {
//            val file = File(analyseCardImagePath)
//            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//            analyseFilePart =
//                MultipartBody.Part.createFormData("analyses", file.name, requestFile)
//        }
//        return service.userRegister(
//            name.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            phone.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            email.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            password.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            nationalId.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            gender.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            region_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            subRegion_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            subSubRegion_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            company_id?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            product_id?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            address.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            health_insurance.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//            identityFilePart,
//            insuranceFilePart,
//            raysFilePart,
//            analyseFilePart,
//            lang.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//        )
//    }

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