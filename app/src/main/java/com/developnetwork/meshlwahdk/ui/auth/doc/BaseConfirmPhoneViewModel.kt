package com.ivestment.doctorna.ui.auth

import androidx.lifecycle.LiveData
import com.ivestment.doctorna.base.BaseViewModel
import com.ivestment.doctorna.data.repository.AuthRepo

abstract class BaseConfirmPhoneViewModel(private val authRepo: AuthRepo) : BaseViewModel() {
   abstract fun confirm(phone: String, code: String) :LiveData<*>

    fun resend(phone: String) = callRequestLiveData { authRepo.resendCode(phone) }
}