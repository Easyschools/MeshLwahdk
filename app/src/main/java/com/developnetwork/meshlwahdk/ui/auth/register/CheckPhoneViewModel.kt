package com.developnetwork.meshlwahdk.ui.auth.register

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.AuthRepo
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CheckPhoneViewModel(private val authRepo: AuthRepo, private val sharedPreferencesManager: SharedPreferencesManager):
    BaseViewModel() {

    fun checkPhone(phone:String)=callRequestWaitLiveData { authRepo.checkPhone(phone) }

    fun getUser(phone:String)=callRequestWaitLiveData { authRepo.getPhoneUser(phone) }

    fun registerPhone(phone:String)=fullHandelRequestLiveData<Boolean> {
        showLoading.postValue(true)

        val result= withContext(Dispatchers.IO) { authRepo.registerPhone(phone) }

        sharedPreferencesManager.saveUserData(result)
        sharedPreferencesManager.isLoggedIn=false

        showLoading.postValue(false)

        emit(true)
    }
}