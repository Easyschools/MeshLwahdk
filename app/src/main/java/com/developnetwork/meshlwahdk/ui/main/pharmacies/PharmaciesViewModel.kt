package com.developnetwork.meshlwahdk.ui.main.pharmacies

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.OtherRepo

class PharmaciesViewModel(private val otherRepo: OtherRepo):BaseViewModel() {
    fun getPharmacies()=callRequestLiveData { otherRepo.getRedemptionCenters() }
}