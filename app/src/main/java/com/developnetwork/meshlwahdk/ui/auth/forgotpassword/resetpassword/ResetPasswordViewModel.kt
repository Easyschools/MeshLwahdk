package com.developnetwork.meshlwahdk.ui.auth.forgotpassword.resetpassword

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.AuthRepo
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager

class ResetPasswordViewModel(private val authRepo: AuthRepo, val sharedPreferencesManager: SharedPreferencesManager) : BaseViewModel() {
    fun resetPassword(phone: String, password: String) =
        callRequestWaitLiveData { authRepo.resetPassword(phone, password, password) }

}