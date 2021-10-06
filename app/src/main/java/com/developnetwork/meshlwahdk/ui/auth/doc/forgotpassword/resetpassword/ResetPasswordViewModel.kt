package com.ivestment.doctorna.ui.auth.forgotpassword.resetpassword

import com.ivestment.doctorna.base.BaseViewModel
import com.ivestment.doctorna.data.repository.AuthRepo

class ResetPasswordViewModel(private val authRepo: AuthRepo) : BaseViewModel() {
    fun resetPassword(phone: String, password: String) =
        callRequestWaitLiveData { authRepo.resetPassword(phone, password, password) }

}