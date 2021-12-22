package com.developnetwork.meshlwahdk.data.repository

import com.developnetwork.meshlwahdk.BuildConfig
import com.developnetwork.meshlwahdk.data.model.Program
import com.developnetwork.meshlwahdk.data.model.RedeemedProgram
import com.developnetwork.meshlwahdk.data.network.Service
import com.ivestment.doctorna.data.model.BaseResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Field
import java.io.File

interface ProgramsRepo {
    suspend fun getProgram(id: Int): Program
    suspend fun getPrograms(): List<Program>

    suspend fun redeemProgram(
        programID: Int,
        pharmacyID: Int,
        barCode: String,
        rxPhotoPath: String?=null,
        receiptPhotoPath: String?
    ): RedeemedProgram

    suspend fun getRedeemedPrograms(userID:Int?=null): List<RedeemedProgram>
    suspend fun isProductRedeemed(userID:Int,programID:Int):Boolean

}

class ProgramsRepoImpl(private val service: Service) : ProgramsRepo {
    override suspend fun getProgram(id: Int): Program {
        return service.getProgram(id).data
    }

    override suspend fun getPrograms(): List<Program> {
        return service.getPrograms(BuildConfig.company_id).data
    }

    override suspend fun redeemProgram(
        programID: Int,
        pharmacyID: Int,
        barCode: String,
        rxPhotoPath: String?,
        receiptPhotoPath: String?
    ): RedeemedProgram {

        var receiptPhotoFilePart:MultipartBody.Part?=null
        var rxPhotoFilePart:MultipartBody.Part?=null
        if (!receiptPhotoPath.isNullOrBlank()) {
            val rxFile = File(rxPhotoPath)
            val rxRequestFile = rxFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
             rxPhotoFilePart =
                MultipartBody.Part.createFormData("RxPhoto", rxFile.name, rxRequestFile)
        }
        if (!receiptPhotoPath.isNullOrBlank()) {
            val receiptFile = File(receiptPhotoPath)
            val receiptRequestFile =
                receiptFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
             receiptPhotoFilePart =
                MultipartBody.Part.createFormData(
                    "ReceiptPhoto",
                    receiptFile.name,
                    receiptRequestFile
                )
        }

        return service.redeemProgram(
            programID.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            pharmacyID.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            barCode.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            rxPhotoFilePart,
            receiptPhotoFilePart,
            "pending".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        ).data
    }

    override suspend fun getRedeemedPrograms(userID:Int?): List<RedeemedProgram> {
        return service.getRedeemedPrograms(userID).data
    }

    override suspend fun isProductRedeemed(userID: Int, programID: Int): Boolean {
        return service.checkRedeemedProduct(userID,programID).data.contains("one")
    }
}