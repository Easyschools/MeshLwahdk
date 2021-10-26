package com.developnetwork.meshlwahdk.ui.main.dose.editdose

import com.developnetwork.meshlwahdk.data.model.Dose
import com.developnetwork.meshlwahdk.data.repository.DoseRepo
import com.developnetwork.meshlwahdk.data.repository.OtherRepo
import com.developnetwork.meshlwahdk.ui.main.dose.BaseDoseInputViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditDoseViewModel(private val doseRepo: DoseRepo, otherRepo: OtherRepo) :
    BaseDoseInputViewModel(otherRepo) {
    lateinit var dose: Dose


    override fun save(productID: Int, frequency: String, end: String) =
        callRequestLiveData { doseRepo.editDose(dose.id, productID, frequency, end) }
}