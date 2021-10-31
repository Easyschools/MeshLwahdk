package com.developnetwork.meshlwahdk.ui.main.dose.doselist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_dose_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DoseListFragment : BaseFragment() {
    private val viewModel: DoseListViewModel by viewModel()
    private lateinit var adapter: DosesListAdapter
    override fun getLayout(): Int {
        return R.layout.fragment_dose_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleProgress(viewModel)
        handleError(viewModel)

        initList()
        handleDosesLiveData()

        addBTN.setOnClickListener {
            findNavController().navigate(DoseListFragmentDirections.actionDoseListFragmentToAddDoseFragment())
        }
    }

    private fun initList() {
        adapter = DosesListAdapter(){
            findNavController().navigate(DoseListFragmentDirections.actionDoseListFragmentToEditDoseFragment(it))
        }
        dosesRV.layoutManager = LinearLayoutManager(requireContext(), )
        dosesRV.adapter = adapter
    }

    private fun handleDosesLiveData() {
        viewModel.getDoses().observe(viewLifecycleOwner, {
            adapter.submitList(it.toMutableList())
        })
    }
}