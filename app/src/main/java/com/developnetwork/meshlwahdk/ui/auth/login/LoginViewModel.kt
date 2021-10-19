package com.developnetwork.meshlwahdk.ui.auth.login

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.AuthRepo
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(private val authRepo: AuthRepo, val sharedPreferencesManager: SharedPreferencesManager) :
    BaseViewModel() {

    fun userLogIn(mobile: String, password: String) = handleRequestLiveData<Boolean> {

        val result= withContext(Dispatchers.IO) { authRepo.login(mobile, password) }


            result?.let {
                sharedPreferencesManager.saveUserData(it)
            }

           emit(true)

//            sendFireBaseToken()
//        } else {
//            if (loginDBResponse.message != null) {
//                Toast.makeText(this, loginDBResponse.message, Toast.LENGTH_SHORT).show()
//                loginDBResponse.message = null
//            }

        }
//
//    fun getPatientProducts(
//        user_id: Int
//    ) = callRequestLiveData { userRepo.getUser(user_id) }
//
//    fun sendToken(
//        deviceToken: String
//    ) = callRequestLiveData { userRepo.sendToken(deviceToken) }

}