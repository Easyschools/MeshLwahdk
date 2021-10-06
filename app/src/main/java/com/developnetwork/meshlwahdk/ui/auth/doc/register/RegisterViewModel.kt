package com.ivestment.doctorna.ui.auth.register

import com.ivestment.doctorna.BuildConfig
import com.ivestment.doctorna.base.BaseViewModel
import com.ivestment.doctorna.data.model.PatientCategory
import com.ivestment.doctorna.data.model.Region
import com.ivestment.doctorna.data.repository.AuthRepo
import com.ivestment.doctorna.data.repository.OtherRepo
import com.ivestment.doctorna.data.repository.UserRepo
import com.ivestment.doctorna.utils.managers.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class RegisterViewModel(
    private val authRepo: AuthRepo,
    private val userRepo: UserRepo,
    private val otherRepo: OtherRepo,
    val sharedPreferencesManager: SharedPreferencesManager
) :
    BaseViewModel() {

    var name: String = ""
    var phone: String = ""
    var email: String = ""
    var age: String = ""
    var nationalId: String = ""
    var password: String = ""
    var gender: Int = 0

    var profilePicPath: String? = null
    var categoryDocumentPath: String? = null
    var identityCardImagePath: String? = null
    var insuranceCardImagePath: String? = null

    fun getCategories(name: String) = handleRequestLiveData<List<PatientCategory>> {
        val result = withContext(Dispatchers.IO) {
            otherRepo.getPatientCategories()
        }

        val list = ArrayList<PatientCategory>()
        list.add(PatientCategory(0, name))
        list.addAll(result)

        emit(list)
    }

    fun getRegions(name: String) = handleRequestLiveData<List<Region>> {
        val result = withContext(Dispatchers.IO) {
            otherRepo.getAllRegions()
        }

        val list = ArrayList<Region>()
        list.add(Region(name = name))
        list.addAll(result)

        emit(list)
    }

    fun getDistricts(regionID: Int, name: String) = handleRequestLiveData<List<Region>> {
        val result = withContext(Dispatchers.IO) {
            otherRepo.getAllDistricts(regionID)
        }

        val list = ArrayList<Region>()
        list.add(Region(name = name))
        list.addAll(result)

        emit(list)
    }

    fun getSubRegions(districtsID: Int, name: String) = handleRequestLiveData<List<Region>> {
        val result = withContext(Dispatchers.IO) {
            otherRepo.getAllSubRegion(districtsID)
        }

        val list = ArrayList<Region>()
        list.add(Region(name = name))
        list.addAll(result)

        emit(list)
    }

    fun getCreateControllerPatient() = callRequestLiveData { authRepo.createControllerPatient() }

    fun completeRegister(
        region_id: Int,
        subRegion_id: Int,
        subSubRegion_id: Int,
        healthInsurance: String,
        categoryID: Int
    ) = callRequestLiveData {
        authRepo.completeRegister(
            name,
            phone,
            email,
            password,
            nationalId,
            gender.toString(),
            region_id,
            subRegion_id,
            subSubRegion_id,
            BuildConfig.company_id,
            BuildConfig.product_id,
            age,
            healthInsurance,
            categoryID,
            profilePicPath,
            categoryDocumentPath,
            identityCardImagePath,
            insuranceCardImagePath
        )
    }

//    fun userRegister(
//        region_id: Int,
//        subRegion_id: Int,
//        subSubRegion_id: Int,
//        healthInsurance:
//    ) = callRequestLiveData {
//        authRepo.userRegister(
//            name,
//            phone,
//            email,
//            password,
//            nationalId,
//            gender,
//            region_id,
//            subRegion_id,
//            subSubRegion_id,
//            null,
//            "1",
//            age,
//            healthInsurance,
//            identityCardImage, insuranceCardImage, raysCardImage,
//            analyseCardImage, lang
//        )
//    }

    fun sendToken(
        deviceToken: String
    ) = callRequestLiveData { userRepo.sendToken(deviceToken) }

    fun getAge(dateString: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sdf.parse(dateString)
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        dob.time = date
//        dob[year, month] = day
        var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
        if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
            age--
        }
        return age.toString()
    }
}