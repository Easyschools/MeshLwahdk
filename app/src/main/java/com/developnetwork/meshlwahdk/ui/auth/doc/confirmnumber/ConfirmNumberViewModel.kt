package com.ivestment.doctorna.ui.auth.confirmnumber

import com.ivestment.doctorna.data.repository.AuthRepo
import com.ivestment.doctorna.ui.auth.BaseConfirmPhoneViewModel

class ConfirmNumberViewModel(private val authRepo: AuthRepo) : BaseConfirmPhoneViewModel(authRepo) {
    override fun confirm(phone: String, code: String) =
        callRequestWaitLiveData { authRepo.confirmPhone(code, phone) }
}