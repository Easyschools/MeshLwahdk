package com.ivestment.doctorna.ui.auth.forgotpassword.confirmphone

import com.ivestment.doctorna.data.repository.AuthRepo
import com.ivestment.doctorna.ui.auth.BaseConfirmPhoneViewModel

class ForgotPasswordConfirmationViewModel(private val authRepo: AuthRepo) :
    BaseConfirmPhoneViewModel(authRepo) {
    override fun confirm(phone: String, code: String) =
        callRequestWaitLiveData { authRepo.confirmForgotPasswordPhone(code, phone) }

}