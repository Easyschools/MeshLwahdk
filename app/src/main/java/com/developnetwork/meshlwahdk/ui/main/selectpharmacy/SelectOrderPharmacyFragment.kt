package com.developnetwork.meshlwahdk.ui.main.selectpharmacy

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.ui.adapters.RegionsAdapter
import com.developnetwork.meshlwahdk.ui.main.pharmacies.PharmaciesAdapter
import kotlinx.android.synthetic.main.fragment_pharmacies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectOrderPharmacyFragment : BaseFragment() {
    private lateinit var adapter: PharmaciesAdapter
    private val viewModel: SelectOrderPharmacyViewModel by viewModel()
    private val args: SelectOrderPharmacyFragmentArgs by navArgs()

    override fun getLayout(): Int {
        return R.layout.fragment_select_order_pharmacy
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleProgress(viewModel)
        handleError(viewModel)

        initList()
        handleCitiesLiveData()
        handlePharmaciesLiveData()
    }

    private fun initList() {
        adapter = PharmaciesAdapter() { _,pharmacyId,name->
            findNavController().navigate(
                SelectOrderPharmacyFragmentDirections.actionSelectOrderPharmacyFragmentToOrderProgramFragment(
                    args.programID,
                    pharmacyId,
                    name
                )
            )
        }
        pharmaciesRV.layoutManager = LinearLayoutManager(requireContext())
        pharmaciesRV.adapter = adapter
    }

    private fun handlePharmaciesLiveData(regionID:Int?=null) {
        viewModel.getPharmacies(args.programID,regionID).observe(viewLifecycleOwner, {
            adapter.submitList(it.toMutableList())
        })
    }
    private fun handleCitiesLiveData() {
        viewModel.getRegions(getString(R.string.city)).observe(viewLifecycleOwner, {
            citySpinner.setAdapter(RegionsAdapter(requireContext(), it))
            citySpinner.onItemSelected {
                if (0 != it)
                    handlePharmaciesLiveData(it)
            }
        })
    }
}