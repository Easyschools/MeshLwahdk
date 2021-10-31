package com.developnetwork.meshlwahdk.ui.main.history

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment() {
    private val viewModel: HistoryViewModel by viewModel()
    private lateinit var adapter: HistoryAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_history
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleProgress(viewModel)
        handleError(viewModel)


        initList()
        handlePharmaciesLiveData()
    }

    private fun initList() {
        adapter = HistoryAdapter()

        historyRV.layoutManager = LinearLayoutManager(requireContext())
        historyRV.adapter = adapter
    }

    private fun handlePharmaciesLiveData() {
        viewModel.getRedeemedPrograms().observe(viewLifecycleOwner, {
            adapter.submitList(it.toMutableList())
        })
    }
}

