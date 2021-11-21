package com.developnetwork.meshlwahdk.ui.splash

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.OtherRepo
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SplashViewModel(
    private val otherRepo: OtherRepo,
    private val sharedPreferencesManager: SharedPreferencesManager
) : BaseViewModel() {
    fun getCompany() = handleRequest{
        val result = withContext(Dispatchers.IO) { otherRepo.getCompany() }

        result.phone?.let {
            sharedPreferencesManager.companyPhone = it
        }

//        emit(result)
    }
}