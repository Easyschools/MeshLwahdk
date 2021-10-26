package com.developnetwork.meshlwahdk.ui.main.dose.adddose

import com.developnetwork.meshlwahdk.data.repository.DoseRepo
import com.developnetwork.meshlwahdk.data.repository.OtherRepo
import com.developnetwork.meshlwahdk.ui.main.dose.BaseDoseInputViewModel

class AddDoseViewModel(private val doseRepo: DoseRepo, otherRepo: OtherRepo):BaseDoseInputViewModel(otherRepo) {
    override fun save(productID: Int, frequency: String, end: String) =
        callRequestLiveData { doseRepo.addDose(productID, frequency, end) }

}