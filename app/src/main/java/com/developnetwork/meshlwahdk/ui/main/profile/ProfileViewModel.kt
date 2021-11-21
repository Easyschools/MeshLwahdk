package com.developnetwork.meshlwahdk.ui.main.profile

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.model.User
import com.developnetwork.meshlwahdk.data.repository.AuthRepo
import com.developnetwork.meshlwahdk.data.repository.UserRepo
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val userRepo: UserRepo,
    private val authRepo: AuthRepo,
    val sharedPreferencesManager: SharedPreferencesManager
) : BaseViewModel() {
    fun forgotPassword(phone: String) = callRequestWaitLiveData { authRepo.forgetPassword(phone) }

    fun getUser() = fullHandelRequestLiveData<User> {
        emit(sharedPreferencesManager.userData)

        val result = withContext(Dispatchers.IO) {
            userRepo.getUser()
        }
        emit(result)
        sharedPreferencesManager.saveUserData(result)
    }
}