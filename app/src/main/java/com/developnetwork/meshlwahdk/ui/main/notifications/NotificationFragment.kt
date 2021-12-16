package com.developnetwork.meshlwahdk.ui.main.notifications

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_notification.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class NotificationFragment : BaseFragment() {
    private val viewModel: NotificationViewModel by viewModel()
    private lateinit var adapter: NotificationsAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_notification
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleError(viewModel)
        handleProgress(viewModel, swipeRefresh)
        handlePagination()

        initNotificationsList()

        taskLiveDataHandler()


        viewModel.loadNextPage()

        swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        handleReadNotifications()
    }

    private fun taskLiveDataHandler() {
        viewModel.notificationsLiveData.observe(viewLifecycleOwner, {
            if (null != it && this::adapter.isInitialized)
                adapter.submitList(it.toMutableList())
        })
    }


    private fun initNotificationsList() {
        adapter = NotificationsAdapter()
        notificationRV.layoutManager = LinearLayoutManager(requireContext())
        notificationRV.adapter = adapter
    }

    private fun handlePagination() {
        notificationRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager!!.childCount
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                val firstVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    viewModel.loadNextPage()
                }
            }
        })
    }

    private fun handleReadNotifications(){
        viewModel.markNotificationRead().observe(viewLifecycleOwner,{})
    }

}