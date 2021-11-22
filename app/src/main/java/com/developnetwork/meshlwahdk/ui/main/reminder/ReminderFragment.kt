package com.developnetwork.meshlwahdk.ui.main.reminder

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_reminder.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReminderFragment : BaseFragment() {
    private val viewModel: ReminderViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_reminder
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleError(viewModel)
        handleProgress(viewModel)

        handleReminders()
    }

    private fun handleReminders() {
        viewModel.getReminders().observe(viewLifecycleOwner, {
            remindersRV.layoutManager = LinearLayoutManager(requireContext())
            remindersRV.adapter = ReminderAdapter(it)
        })
    }
}