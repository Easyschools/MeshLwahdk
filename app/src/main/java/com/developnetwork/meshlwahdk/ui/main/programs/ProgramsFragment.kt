package com.developnetwork.meshlwahdk.ui.main.programs

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_pharmacies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProgramsFragment : BaseFragment() {

    private val viewModel: ProgramsViewModel by viewModel()
    private lateinit var adapter: ProgramsAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_programs
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleProgress(viewModel)
        handleError(viewModel)

        initList()
        handlePharmaciesLiveData()
    }

    private fun initList() {
        adapter = ProgramsAdapter {
            findNavController().navigate(ProgramsFragmentDirections.actionProgramsFragmentToProgramFragment(it,0,""))
        }
        pharmaciesRV.layoutManager = LinearLayoutManager(requireContext())
        pharmaciesRV.adapter = adapter
    }

    private fun handlePharmaciesLiveData() {
        viewModel.getPrograms().observe(viewLifecycleOwner, {
            adapter.submitList(it.toMutableList())
        })
    }

}