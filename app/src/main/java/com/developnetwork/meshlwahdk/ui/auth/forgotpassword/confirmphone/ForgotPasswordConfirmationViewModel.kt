package com.developnetwork.meshlwahdk.ui.auth.forgotpassword.confirmphone

import com.developnetwork.meshlwahdk.data.repository.AuthRepo
import com.developnetwork.meshlwahdk.ui.auth.BaseConfirmPhoneViewModel

class ForgotPasswordConfirmationViewModel(private val authRepo: AuthRepo) :
    BaseConfirmPhoneViewModel(authRepo) {
    override fun confirm(phone: String, code: String) =
        callRequestWaitLiveData { authRepo.confirmForgotPasswordPhone(code, phone) }

}