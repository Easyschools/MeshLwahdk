package com.developnetwork.meshlwahdk.ui.main.orderprogram

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.ProgramsRepo
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrderProgramsViewModel(
    private val programsRepo: ProgramsRepo,
    private val sharedPreferencesManager: SharedPreferencesManager
) : BaseViewModel() {
    var rxPath: String? = null
    var receiptPath: String? = null
    var productCode: String = "xxx"
    var isProductRedeemed = false

    fun redeemProgram(programID: Int, pharmacyID: Int) = callRequestLiveData {
        programsRepo.redeemProgram(
            programID,
            pharmacyID,
            productCode,
            rxPath,
            receiptPath
        )
    }

    fun checkProductRedeemed(programID: Int) = handleRequestLiveData<Boolean> {
        val result = withContext(Dispatchers.IO) {
            programsRepo.isProductRedeemed(sharedPreferencesManager.userID, programID)
        }
        isProductRedeemed = result

        emit(result)
    }
}