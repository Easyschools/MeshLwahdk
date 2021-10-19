package com.developnetwork.meshlwahdk.ui.auth.forgotpassword

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.AuthRepo

class ForgotPasswordViewModel(private val authRepo: AuthRepo) : BaseViewModel() {
    fun forgotPassword(phone: String) = callRequestWaitLiveData { authRepo.forgetPassword(phone) }
}