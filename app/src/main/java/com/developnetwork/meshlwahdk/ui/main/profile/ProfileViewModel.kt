package com.developnetwork.meshlwahdk.ui.main.profile

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.AuthRepo
import com.developnetwork.meshlwahdk.data.repository.UserRepo
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager

class ProfileViewModel(private val userRepo: UserRepo,private val authRepo: AuthRepo,val sharedPreferencesManager: SharedPreferencesManager):BaseViewModel() {
    fun forgotPassword(phone: String) = callRequestWaitLiveData { authRepo.forgetPassword(phone) }
}