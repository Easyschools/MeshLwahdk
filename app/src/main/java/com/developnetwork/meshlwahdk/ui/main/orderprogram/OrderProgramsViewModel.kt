package com.developnetwork.meshlwahdk.ui.main.orderprogram

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.ProgramsRepo

class OrderProgramsViewModel(private val programsRepo: ProgramsRepo) : BaseViewModel() {
    var rxPath: String? = null
    var receiptPath: String? = null
    var productCode: String = "xxx"

    fun redeemProgram(programID: Int,pharmacyID:Int) = callRequestLiveData {
        programsRepo.redeemProgram(
            programID,
            pharmacyID,
            productCode,
            rxPath!!,
            receiptPath
        )
    }
}