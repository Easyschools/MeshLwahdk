package com.developnetwork.meshlwahdk.ui.main.mainActivty

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.NotificationsRepo
import com.developnetwork.meshlwahdk.data.repository.UserRepo

class MainViewModel(
    private val userRepo: UserRepo,
    private val notificationsRepo: NotificationsRepo
) : BaseViewModel() {

    fun updateLanguage() = callRequestLiveData { userRepo.updateUserLang() }

    fun getNotificationCount() = callRequestLiveData { notificationsRepo.getNotificationsCount() }
}