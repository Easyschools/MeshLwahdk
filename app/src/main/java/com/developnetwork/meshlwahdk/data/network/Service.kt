package com.developnetwork.meshlwahdk.data.network

import com.developnetwork.meshlwahdk.data.model.User
import com.ivestment.doctorna.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface Service {
    @POST("patients-login")
    @FormUrlEncoded
    suspend fun userLogin(
            @Field("phone") email: String,
            @Field("password") password: String
    ): BaseResponse<User>

    @POST("phoneRegister")
    @FormUrlEncoded
    suspend fun phoneRegister(@Field("phone") phone: String): BaseResponse<User>

//    @GET("patient-register/create")
//    suspend fun createControllerPatient(): CreateControllerPatientDBResponse

    @POST("checkPhone")
    @FormUrlEncoded
    suspend fun checkPhone(@Field("phone") phone: String): BaseResponse<Boolean>

    @POST("getPhoneUser")
    @FormUrlEncoded
    suspend fun getPhoneUser(@Field("phone") phone: String): BaseResponse<User>

    @POST("resend")
    @FormUrlEncoded
    suspend fun resendCode(@Field("phone") phone: String): BaseResponse<Any>

    @POST("confirmAccount")
    @FormUrlEncoded
    suspend fun confirmPhone(@Field("phone") phone: String, @Field("confirmation_code") code: String): BaseResponse<String>

    @POST("checkCode")
    @FormUrlEncoded
    suspend fun confirmForgotPasswordPhone(@Field("phone") phone: String, @Field("token") code: String): BaseResponse<String>

    @POST("patient-category")
    suspend fun getPatientCategories():BaseResponse<List<PatientCategory>>

    @POST("completeRegister")
    @Multipart
    suspend fun completeRegister(
            @Part("displayName") name: RequestBody,
            @Part("phone") phone: RequestBody,
            @Part("email") email: RequestBody,
            @Part("password") password: RequestBody,
            @Part("national_id") nationalId: RequestBody,
            @Part("gender") gender: RequestBody,
            @Part("region_id") region_id: RequestBody,
            @Part("districts_id") subRegion_id: RequestBody,
            @Part("subRegion_id") subSubRegion_id: RequestBody,
            @Part("company_id") company_id: RequestBody,
            @Part("product_id") product_id: RequestBody,
            @Part("Age") age: RequestBody,
            @Part("health_insurance") health_insurance: RequestBody,
            @Part("category_id") Category_id: RequestBody,
            @Part profilePic: MultipartBody.Part?,
            @Part categoryDocumentImage: MultipartBody.Part?,
            @Part identityCardImage: MultipartBody.Part?,
            @Part insuranceCardImage: MultipartBody.Part?
    ): BaseResponse<User>

    @POST("forgetPassword")
    @FormUrlEncoded
    suspend fun forgetPassword(@Field("phone") phone: String): BaseResponse<Any>

    @POST("resetPassword")
    @FormUrlEncoded
    suspend fun resetPassword(@Field("phone") phone: String,@Field("new_password") newPassword: String,@Field("confirm_password") confirmPassword: String): BaseResponse<Any>

    @POST("insurance-create")
    @Multipart
    suspend fun addInsuranceCard(@Part insuranceCardImage: MultipartBody.Part):BaseResponse<Any>

    @POST("My-Insurance")
    suspend fun getUserInsuranceStat():BaseResponse<InsuranceCard>

//    @POST("completeRegister")
//    @Multipart
//    suspend fun userRegister(
//            @Part("displayName") name: RequestBody?,
//            @Part("phone") phone: RequestBody?,
//            @Part("email") email: RequestBody?,
//            @Part("password") password: RequestBody?,
//            @Part("national_id") nationalId: RequestBody?,
//            @Part("gender") gender: RequestBody?,
//            @Part("region_id") region_id: RequestBody?,
//            @Part("districts_id") subRegion_id: RequestBody?,
//            @Part("subRegion_id") subSubRegion_id: RequestBody?,
//            @Part("company_id") company_id: RequestBody?,
//            @Part("product_id") product_id: RequestBody?,
//            @Part("age") address: RequestBody?,
//            @Part("health_insurance") health_insurance: RequestBody?,
//            @Part identityCardImage: MultipartBody.Part?,
//            @Part insuranceCardImage: MultipartBody.Part?,
//            @Part raysCardImage: MultipartBody.Part?,
//            @Part analyseCardImage: MultipartBody.Part?,
//            @Part("lang") lang: RequestBody?
//    ): RegisterDBResponse


    @POST("getAllReg")
    suspend fun getAllRegions(): BaseResponse<List<Region>>

    @POST("getAllDis")
    @FormUrlEncoded
    suspend fun getAllDistricts(@Field("region_id") region_id: Int): BaseResponse<List<Region>>

    @POST("getAllSubReg")
    @FormUrlEncoded
    suspend fun getAllSubRegion(@Field("district_id") district_id: Int): BaseResponse<List<Region>>

//    @GET("patients_data/{user_id}")
//    suspend fun getPatientsProducts(
//            @Path("user_id") user_id: Int
//    ): PatientsProductsDBResponse
//
//    @POST("send-token")
//    @FormUrlEncoded
//    suspend fun sendToken(
//            @Field("fcm_token") device_token: String?
//    ): TokenDBResponse
//
//    @GET("patient-notification-get/{user_id}")
//    suspend fun getPatientsNotifications(
//            @Path("user_id") user_id: Int
//    ): NotificationDBResponse
//
//    @POST("add-image")
//    @Multipart
//    suspend fun addImage(
//            @Part("type") type: RequestBody?,
//            @Part image: MultipartBody.Part?
//    ): AddImageDBResponse


//    @POST("hub-appointment-create")
//    @FormUrlEncoded
//    suspend fun bookingDoctor(@Field("appointment_date") appointment_date: String?, @Field("hub_id") hub_id: Int, @Field("patient_id") patient_id: Int): BookingDBResponse
//
//    @POST("hub-appointment")
//    @FormUrlEncoded
//    suspend fun reservationDoctor(
//            @Field("appointment_date") appointment_date: String?,
//            @Field("patient_id") patient_id: Int,
//            @Field("appointment_slote_id") appointment_slote_id: Int
//    ): ReservationDBResponse
//
//    @GET("patient-appointment-get/{hub_id}")
//    suspend fun getPatientsAppointment(
//            @Path("hub_id") hub_id: Int
//    ): PatientAppointmentDBResponse

}