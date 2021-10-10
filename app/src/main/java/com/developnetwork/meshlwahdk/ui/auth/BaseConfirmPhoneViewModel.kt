package com.developnetwork.meshlwahdk.ui.auth

import androidx.lifecycle.LiveData
import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.AuthRepo

abstract class BaseConfirmPhoneViewModel(private val authRepo: AuthRepo) : BaseViewModel() {
   abstract fun confirm(phone: String, code: String) :LiveData<*>

    fun resend(phone: String) = callRequestLiveData { authRepo.resendCode(phone) }
}