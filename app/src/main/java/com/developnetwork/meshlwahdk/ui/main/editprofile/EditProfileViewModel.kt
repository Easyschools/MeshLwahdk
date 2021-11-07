package com.developnetwork.meshlwahdk.ui.main.editprofile

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.OtherRepo
import com.developnetwork.meshlwahdk.data.repository.UserRepo
import com.ivestment.doctorna.data.model.Region
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class EditProfileViewModel(private val userRepo: UserRepo ,   private val otherRepo: OtherRepo) : BaseViewModel() {
    var profilePicPath: String? = null


    fun getUser()=callRequestLiveData { userRepo.getUser() }

    fun editProfile(
        name: String,
        gender: String,
        region_id: Int,
        subRegion_id: Int,
        birthDate: String
    ) = callRequestLiveData {
        userRepo.editProfile(
            name,
            gender,
            region_id,
            subRegion_id,
            getAge(birthDate),
            profilePicPath
        )
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
    private fun getAge(dateString: String): String {
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