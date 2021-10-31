package com.developnetwork.meshlwahdk.ui.main.selectpharmacy

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.OtherRepo

class SelectOrderPharmacyViewModel(private val otherRepo: OtherRepo):BaseViewModel() {
    fun getPharmacies()=callRequestLiveData { otherRepo.getRedemptionCenters() }
}