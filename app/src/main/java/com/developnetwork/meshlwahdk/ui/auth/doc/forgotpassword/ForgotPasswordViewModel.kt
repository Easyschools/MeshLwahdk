package com.ivestment.doctorna.ui.auth.forgotpassword

import com.ivestment.doctorna.base.BaseViewModel
import com.ivestment.doctorna.data.repository.AuthRepo

class ForgotPasswordViewModel(private val authRepo: AuthRepo) : BaseViewModel() {
    fun forgotPassword(phone: String) = callRequestWaitLiveData { authRepo.forgetPassword(phone) }
}