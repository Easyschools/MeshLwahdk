package com.developnetwork.meshlwahdk.ui.auth.forgotpassword.resetpassword

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.AuthRepo

class ResetPasswordViewModel(private val authRepo: AuthRepo) : BaseViewModel() {
    fun resetPassword(phone: String, password: String) =
        callRequestWaitLiveData { authRepo.resetPassword(phone, password, password) }

}