package com.developnetwork.meshlwahdk.ui.auth.login

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.AuthRepo
import com.developnetwork.meshlwahdk.data.repository.UserRepo
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(private val authRepo: AuthRepo, private val userRepo: UserRepo, val sharedPreferencesManager: SharedPreferencesManager) :
    BaseViewModel() {

    fun userLogIn(
        mobile: String,
        password: String) = handleRequestLiveData<Boolean> {
        val result= withContext(Dispatchers.IO)
        { authRepo.login(mobile, password) }

        if (result != null) {
            sharedPreferencesManager.isLoggedIn = true
            sharedPreferencesManager.userToken = result.token
            sharedPreferencesManager.userID = result.id
            result.name?.let { name ->
                sharedPreferencesManager.userName = name
            }

//            sendFireBaseToken()
           emit(true)
//        } else {
//            if (loginDBResponse.message != null) {
//                Toast.makeText(this, loginDBResponse.message, Toast.LENGTH_SHORT).show()
//                loginDBResponse.message = null
//            }
        }
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