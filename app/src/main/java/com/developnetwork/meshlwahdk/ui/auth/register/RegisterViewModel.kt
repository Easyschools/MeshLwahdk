package com.developnetwork.meshlwahdk.ui.auth.register

import android.util.Log
import com.developnetwork.meshlwahdk.BuildConfig
import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.model.User
import com.developnetwork.meshlwahdk.data.repository.AuthRepo
import com.developnetwork.meshlwahdk.data.repository.OtherRepo
import com.developnetwork.meshlwahdk.data.repository.ProgramsRepo
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import com.google.firebase.messaging.FirebaseMessaging
import com.ivestment.doctorna.data.model.PatientCategory
import com.ivestment.doctorna.data.model.Region
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class RegisterViewModel(
    private val authRepo: AuthRepo,
    private val otherRepo: OtherRepo,
    private val programsRepo: ProgramsRepo,
    val sharedPreferencesManager: SharedPreferencesManager
) :
    BaseViewModel() {
    init {
        getNotificationToken()
    }
    var name: String = ""
    var phone: String = ""
    var email: String? = null
    var age: String = ""
    var nationalId: String = ""
    var password: String = ""
    var gender: Int = 0
    var region_id: Int = 1
    var subRegion_id: Int = 1

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


    fun completeRegister(
        productID: Int
    ) = handleRequestLiveData<User> {
        val result= withContext(Dispatchers.IO){authRepo.completeRegister(
            name,
            phone,
            email,
            password,
            nationalId,
            gender.toString(),
            region_id,
            subRegion_id,
            BuildConfig.company_id,
            productID,
            age,
            "xxxx",
            1,
            profilePicPath,
            categoryDocumentPath,
            identityCardImagePath,
            insuranceCardImagePath,
            sharedPreferencesManager.notificationToken
        )}

        sharedPreferencesManager.saveUserData(result)

        emit(result)
    }

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

    fun getPrograms() = callRequestLiveData { programsRepo.getPrograms() }

    private fun getNotificationToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.tag("NotificationToken")
                    .d(task.exception, "getInstanceId failed")
                return@addOnCompleteListener
            }
            // Get new Instance ID token
            val notificationToken = task.result
            notificationToken?.let {
                sharedPreferencesManager.notificationToken = it
                Timber.tag("NotificationToken").d(notificationToken)
                Log.d("NotificationToken", notificationToken)
            }
        }
    }

}