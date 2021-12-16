package com.developnetwork.meshlwahdk.ui.main.notifications

import androidx.lifecycle.MutableLiveData
import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.model.Notification
import com.developnetwork.meshlwahdk.data.repository.NotificationsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationViewModel(private val notificationsRepo: NotificationsRepo):BaseViewModel() {
    val notificationsLiveData = MutableLiveData<MutableList<Notification>>()
    var mCurrentPage = 0
    var mTotalPage = 1

    init {
        notificationsLiveData.value = ArrayList<Notification>()
    }

    private fun getNotifications() = handleRequest {
        val result = withContext(Dispatchers.IO) {
            notificationsRepo.getNotifications(mCurrentPage)
        }
        if (mCurrentPage == 1)
            showNoData.postValue(result.data.isEmpty())

        result.data.let { notificationsLiveData.value?.addAll(it) }
        notificationsLiveData.value = notificationsLiveData.value

        mCurrentPage = result.currentPage
        mTotalPage = result.lastPage
    }


    fun loadNextPage() {
        if (mCurrentPage < mTotalPage) {
            mCurrentPage++
            getNotifications()
        }
    }

    fun refresh() {
        mCurrentPage = 1
        notificationsLiveData.value?.clear()
        getNotifications()
    }
     fun markNotificationRead()=callRequestLiveData { notificationsRepo.readNotifications() }
}