package com.developnetwork.meshlwahdk.data.repository

import com.developnetwork.meshlwahdk.BuildConfig
import com.developnetwork.meshlwahdk.data.model.Company
import com.developnetwork.meshlwahdk.data.model.Product
import com.developnetwork.meshlwahdk.data.model.RedemptionCenter
import com.developnetwork.meshlwahdk.data.network.Service
import com.ivestment.doctorna.data.model.InsuranceCard
import com.ivestment.doctorna.data.model.PatientCategory
import com.ivestment.doctorna.data.model.Region
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

interface OtherRepo {
    suspend fun getAllRegions(): List<Region>

    suspend fun getAllDistricts(region_id: Int): List<Region>

    suspend fun getAllSubRegion(region_id: Int): List<Region>

    suspend fun addInsuranceCard(insuranceCardImagePath: String): Any

    suspend fun getUserInsuranceStat(): InsuranceCard

    suspend fun getPatientCategories(): List<PatientCategory>

    suspend fun getProducts(company_id: Int): List<Product>

    suspend fun getRedemptionCenters(programID:Int?=null,regionID:Int?=null): List<RedemptionCenter>

    suspend fun getCompany(): Company

}

class OthersRepoImpl(private val service: Service) : OtherRepo {
    override suspend fun getAllRegions(): List<Region> {
        return service.getAllRegions().data
    }

    override suspend fun getAllDistricts(region_id: Int): List<Region> {
        return service.getAllDistricts(region_id).data
    }

    override suspend fun getAllSubRegion(region_id: Int): List<Region> {
        return service.getAllSubRegion(region_id).data
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

    override suspend fun getProducts(company_id: Int): List<Product> {
        return service.getProducts(company_id).data
    }

    override suspend fun getRedemptionCenters(programID:Int?,regionID:Int?): List<RedemptionCenter> {
        return service.getRedemptionCenters("pharmacy",9,regionID).data
    }

    override suspend fun getCompany(): Company {
        return service.getCompany(BuildConfig.company_id).data
    }
}