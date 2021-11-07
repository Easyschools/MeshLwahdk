package com.developnetwork.meshlwahdk.ui.main.changephone.confirm

import com.developnetwork.meshlwahdk.data.repository.AuthRepo
import com.developnetwork.meshlwahdk.ui.auth.BaseConfirmPhoneViewModel

class ConfirmUpdatedPhoneViewModel(private val authRepo: AuthRepo):BaseConfirmPhoneViewModel(authRepo) {
    override fun confirm(phone: String, code: String) =
        callRequestWaitLiveData { authRepo.confirmPhone(code, phone) }
}