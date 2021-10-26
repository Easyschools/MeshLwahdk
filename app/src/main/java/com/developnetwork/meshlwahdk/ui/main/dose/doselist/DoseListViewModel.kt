package com.developnetwork.meshlwahdk.ui.main.dose.doselist

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.DoseRepo

class DoseListViewModel(private val doseRepo: DoseRepo):BaseViewModel() {

    fun getDoses()=callRequestLiveData { doseRepo.getDoses() }
}