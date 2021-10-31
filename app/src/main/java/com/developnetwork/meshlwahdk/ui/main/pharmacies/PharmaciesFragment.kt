package com.developnetwork.meshlwahdk.ui.main.pharmacies

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_pharmacies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PharmaciesFragment : BaseFragment() {
    private val viewModel: PharmaciesViewModel by viewModel()
    private lateinit var adapter: PharmaciesAdapter
    override fun getLayout(): Int {
        return R.layout.fragment_pharmacies
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleProgress(viewModel)
        handleError(viewModel)

        initList()
        handlePharmaciesLiveData()
    }

    private fun initList() {
        adapter = PharmaciesAdapter(){programID,pharmacyId,name->
            findNavController().navigate(PharmaciesFragmentDirections.actionPharmaciesFragmentToProgramFragment(programID,pharmacyId,name))
        }
        pharmaciesRV.layoutManager = GridLayoutManager(requireContext(), 2)
        pharmaciesRV.adapter = adapter
    }

    private fun handlePharmaciesLiveData() {
        viewModel.getPharmacies().observe(viewLifecycleOwner, {
            adapter.submitList(it.toMutableList())
        })
    }

}