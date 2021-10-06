package com.ivestment.doctorna.data.repository

import com.ivestment.doctorna.data.model.InsuranceCard
import com.ivestment.doctorna.data.model.PatientCategory
import com.ivestment.doctorna.data.model.Region
import com.ivestment.doctorna.data.network.Service
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

interface OtherRepo {
    suspend fun getAllRegions(): List<Region>

    suspend fun getAllDistricts(region_id: Int): List<Region>

    suspend fun getAllSubRegion(district_id: Int): List<Region>

    suspend fun addInsuranceCard(insuranceCardImagePath: String): Any

    suspend fun getUserInsuranceStat():InsuranceCard

    suspend fun getPatientCategories(): List<PatientCategory>

}

class OthersRepoImpl(private val service: Service) : OtherRepo {
    override suspend fun getAllRegions(): List<Region> {
        return service.getAllRegions().data
    }

    override suspend fun getAllDistricts(region_id: Int): List<Region> {
        return service.getAllDistricts(region_id).data
    }

    override suspend fun getAllSubRegion(district_id: Int): List<Region> {
        return service.getAllSubRegion(district_id).data
    }

    override suspend fun addInsuranceCard(insuranceCardImagePath: String): Any {

        val file = File(insuranceCardImagePath)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val insuranceFilePart =
            MultipartBody.Part.createFormData("img", file.name, requestFile)

        return service.addInsuranceCard(insuranceFilePart)
    }

    override suspend fun getUserInsuranceStat(): InsuranceCard {
        return service.getUserInsuranceStat().data
    }

    override suspend fun getPatientCategories(): List<PatientCategory> {
        return service.getPatientCategories().data
    }

}