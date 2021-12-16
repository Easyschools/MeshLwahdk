package com.developnetwork.meshlwahdk.data.repository

import com.developnetwork.meshlwahdk.data.model.BasePagination
import com.developnetwork.meshlwahdk.data.model.Notification
import com.developnetwork.meshlwahdk.data.network.Service

interface NotificationsRepo {
    suspend fun getNotificationsCount(): Int

    suspend fun readNotifications(): Any

    suspend fun getNotifications(page :Int): BasePagination<Notification>
}

class NotificationsRepoImpl(private val service: Service):NotificationsRepo{
    override suspend fun getNotificationsCount(): Int {
        return service.getNotificationsCount().data
    }

    override suspend fun readNotifications(): Any {
        return service.readNotifications().data
    }

    override suspend fun getNotifications(page :Int): BasePagination<Notification> {
        return service.getNotifications(page).data
    }
}