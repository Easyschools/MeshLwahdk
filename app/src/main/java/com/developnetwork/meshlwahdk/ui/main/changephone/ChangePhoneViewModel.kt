package com.developnetwork.meshlwahdk.ui.main.changephone

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.UserRepo

class ChangePhoneViewModel(private val userRepo: UserRepo):BaseViewModel() {

    fun updatePhone(phone:String)=callRequestLiveData { userRepo.updatePhoneNumber(phone) }
}