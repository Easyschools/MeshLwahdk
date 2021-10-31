package com.developnetwork.meshlwahdk.data.repository

import com.developnetwork.meshlwahdk.data.model.Program
import com.developnetwork.meshlwahdk.data.model.RedeemedProgram
import com.developnetwork.meshlwahdk.data.network.Service
import com.ivestment.doctorna.data.model.BaseResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

interface ProgramsRepo {
    suspend fun getProgram(id: Int): Program
    suspend fun getPrograms(): List<Program>

    suspend fun redeemProgram(
        programID: Int,
        pharmacyID: Int,
        barCode: String,
        rxPhotoPath: String,
        receiptPhotoPath: String
    ): BaseResponse<Any>

    suspend fun getRedeemedPrograms():List<RedeemedProgram>

}

class ProgramsRepoImpl(private val service: Service) : ProgramsRepo {
    override suspend fun getProgram(id: Int): Program {
        return service.getProgram(id).data
    }

    override suspend fun getPrograms(): List<Program> {
        return service.getPrograms().data
    }

    override suspend fun redeemProgram(
        programID: Int,
        pharmacyID: Int,
        barCode: String,
        rxPhotoPath: String,
        receiptPhotoPath: String
    ): BaseResponse<Any> {


        val rxFile = File(rxPhotoPath)
        val rxRequestFile = rxFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val rxPhotoFilePart =
            MultipartBody.Part.createFormData("RxPhoto", rxFile.name, rxRequestFile)


        val receiptFile = File(receiptPhotoPath)
        val receiptRequestFile =
            receiptFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val receiptPhotoFilePart =
            MultipartBody.Part.createFormData("ReceiptPhoto", receiptFile.name, receiptRequestFile)


        return service.redeemProgram(
            programID.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            pharmacyID.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            barCode.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            rxPhotoFilePart,
            receiptPhotoFilePart,
            "pending".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        )
    }

    override suspend fun getRedeemedPrograms(): List<RedeemedProgram> {
        return service.getRedeemedPrograms().data
    }
}