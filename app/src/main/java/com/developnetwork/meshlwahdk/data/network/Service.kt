package com.developnetwork.meshlwahdk.data.network

import com.developnetwork.meshlwahdk.data.model.*
import com.ivestment.doctorna.data.model.BaseResponse
import com.ivestment.doctorna.data.model.InsuranceCard
import com.ivestment.doctorna.data.model.PatientCategory
import com.ivestment.doctorna.data.model.Region
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface Service {
    @POST("patients-login")
    @FormUrlEncoded
    suspend fun userLogin(
        @Field("phone") email: String,
        @Field("password") password: String,
        @Field("company_id") company_id: Int,
        @Field("fcm_token") notificationToken: String?
    ): BaseResponse<User>

    @POST("phoneRegister")
    @FormUrlEncoded
    suspend fun phoneRegister(
        @Field("phone") phone: String,
        @Field("company_id") company_id: Int
    ): BaseResponse<User>


    @POST("checkPhone")
    @FormUrlEncoded
    suspend fun checkPhone(
        @Field("phone") phone: String,
        @Field("company_id") company_id: Int
    ): BaseResponse<Boolean>

    @POST("getPhoneUser")
    @FormUrlEncoded
    suspend fun getPhoneUser(
        @Field("phone") phone: String, @Field("company_id") company_id: Int,
    ): BaseResponse<User>

    @POST("resend")
    @FormUrlEncoded
    suspend fun resendCode(
        @Field("phone") phone: String,
        @Field("company_id") company_id: Int
    ): BaseResponse<Any>

    @POST("confirmAccount")
    @FormUrlEncoded
    suspend fun confirmPhone(
        @Field("phone") phone: String,
        @Field("company_id") company_id: Int,
        @Field("confirmation_code") code: String
    ): BaseResponse<String>

    @POST("checkCode")
    @FormUrlEncoded
    suspend fun confirmForgotPasswordPhone(
        @Field("phone") phone: String,
        @Field("company_id") company_id: Int,
        @Field("token") code: String
    ): BaseResponse<String>

    @POST("patient-category")
    suspend fun getPatientCategories(): BaseResponse<List<PatientCategory>>

    @POST("completeRegister")
    @Multipart
    suspend fun completeRegister(
        @Part("displayName") name: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("email") email: RequestBody?,
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
        @Part insuranceCardImage: MultipartBody.Part?,
        @Part("fcm_token") notificationToken: RequestBody?
    ): BaseResponse<User>


    @POST("forgetPassword")
    @FormUrlEncoded
    suspend fun forgetPassword(
        @Field("phone") phone: String,
        @Field("company_id") company_id: Int
    ): BaseResponse<Any>

    @POST("resetPassword")
    @FormUrlEncoded
    suspend fun resetPassword(
        @Field("phone") phone: String,
        @Field("new_password") newPassword: String,
        @Field("confirm_password") confirmPassword: String
    ): BaseResponse<Any>

    @POST("user/patient")
    suspend fun getPatient(): BaseResponse<User>

    @POST("insurance-create")
    @Multipart
    suspend fun addInsuranceCard(@Part insuranceCardImage: MultipartBody.Part): BaseResponse<Any>

    @POST("My-Insurance")
    suspend fun getUserInsuranceStat(): BaseResponse<InsuranceCard>

    @POST("getByCompany")
    suspend fun getProducts(@Query("company_id") company_id: Int): BaseResponse<List<Product>>


    @POST("getAllReg")
    suspend fun getAllRegions(): BaseResponse<List<Region>>

    @POST("getAllDis")
    @FormUrlEncoded
    suspend fun getAllDistricts(@Field("region_id") region_id: Int): BaseResponse<List<Region>>

    @POST("getAllSubReg")
    @FormUrlEncoded
    suspend fun getAllSubRegion(@Field("district_id") district_id: Int): BaseResponse<List<Region>>

    @GET("redemption")
    suspend fun getRedemptionCenters(
        @Query("type") type: String? = null,
        @Query("program_id") programID: Int? = null,
        @Query("region_id") regionID: Int? = null
    ): BaseResponse<List<RedemptionCenter>>


    @POST("dose/create")
    @FormUrlEncoded
    suspend fun addDose(
        @Field("product_id") product_id: Int,
        @Field("frequency") frequency: String,
        @Field("duration") duration: String
    ): BaseResponse<Dose>

    @POST("dose/update")
    @FormUrlEncoded
    suspend fun editDose(
        @Field("id") id: Int,
        @Field("product_id") product_id: Int,
        @Field("frequency") frequency: String,
        @Field("duration") duration: String
    ): BaseResponse<Dose>


    @POST("dose/get")
    suspend fun getDose(@Query("id") id: Int): BaseResponse<Dose>

    @POST("dose/getAll")
    suspend fun getDoses(): BaseResponse<List<Dose>>

    @POST("getPharmacy")
    suspend fun getPharmacy(@Query("id") id: Int): BaseResponse<RedemptionCenter>

    @GET("program/{id}")
    suspend fun getProgram(@Path("id") id: Int): BaseResponse<Program>

    @GET("program")
    suspend fun getPrograms(@Query("company_id") company_id: Int): BaseResponse<List<Program>>

    @POST("redimedProduct/create")
    @Multipart
    suspend fun redeemProgram(
        @Part("program_id") programID: RequestBody,
        @Part("redmption_id") redmptionID: RequestBody,
        @Part("BarCode") barCode: RequestBody,
        @Part rxPhoto: MultipartBody.Part,
        @Part receiptPhoto: MultipartBody.Part?,
        @Part("status") status: RequestBody
    ): BaseResponse<RedeemedProgram>


    @POST("editProfile")
    @Multipart
    suspend fun editProfile(
        @Part("displayName") name: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("region_id") region_id: RequestBody,
        @Part("districts_id") subRegion_id: RequestBody,
        @Part("subRegion_id") subSubRegion_id: RequestBody,
        @Part("Age") age: RequestBody,
        @Part profilePic: MultipartBody.Part?
    ): BaseResponse<User>

    @POST("redimedProduct/getAll")
    suspend fun getRedeemedPrograms(@Query("user_id") userID:Int?=null): BaseResponse<List<RedeemedProgram>>


    @POST("api/user/update/phone")
    @FormUrlEncoded
    suspend fun updatePhoneNumber(@Field("phone") phone: String): BaseResponse<Any>

    @POST("company/getById")
    suspend fun getCompany(@Query("id") id: Int): BaseResponse<Company>
}