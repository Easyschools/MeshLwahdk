package com.developnetwork.meshlwahdk.ui.main.pharmacies

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.OtherRepo
import com.ivestment.doctorna.data.model.Region
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class PharmaciesViewModel(private val otherRepo: OtherRepo):BaseViewModel() {
    fun getPharmacies(regionID:Int?=null)=callRequestLiveData { otherRepo.getRedemptionCenters(regionID = regionID) }

    fun getRegions(name: String) = handleRequestLiveData<List<Region>> {
        val result = withContext(Dispatchers.IO) {
            otherRepo.getAllRegions()
        }

        val list = ArrayList<Region>()
        list.add(Region(name = name))
        list.addAll(result)

        emit(list)
    }
}