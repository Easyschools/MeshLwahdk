package com.developnetwork.meshlwahdk.ui.auth.login

import android.util.Log
import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.AuthRepo
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class LoginViewModel(private val authRepo: AuthRepo, val sharedPreferencesManager: SharedPreferencesManager) :
    BaseViewModel() {
init {
    getNotificationToken()
}
    fun userLogIn(mobile: String, password: String) = handleRequestLiveData<Boolean> {

        val result= withContext(Dispatchers.IO) { authRepo.login(mobile, password,sharedPreferencesManager.notificationToken) }


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


    private fun getNotificationToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.tag("NotificationToken")
                    .d(task.exception, "getInstanceId failed")
                return@addOnCompleteListener
            }
            // Get new Instance ID token
            val notificationToken = task.result
            notificationToken?.let {
                sharedPreferencesManager.notificationToken = it
                Timber.tag("NotificationToken").d(notificationToken)
                Log.d("NotificationToken", notificationToken)
            }
        }
    }

}