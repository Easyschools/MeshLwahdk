package com.developnetwork.meshlwahdk.ui.main.pharmacies

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.ui.adapters.RegionsAdapter
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
        handleCitiesLiveData()
        handlePharmaciesLiveData()
    }

    private fun initList() {
        adapter = PharmaciesAdapter(){programID,pharmacyId,name->
          if(programID!=0)
            findNavController().navigate(PharmaciesFragmentDirections.actionPharmaciesFragmentToProgramFragment(programID,pharmacyId,name))
            else{
                showError(R.string.this_pharmacy_doesnt_have_programs)
          }
        }
        pharmaciesRV.layoutManager = LinearLayoutManager(requireContext())
        pharmaciesRV.adapter = adapter
    }

    private fun handlePharmaciesLiveData(regionID:Int?=null) {
        viewModel.getPharmacies(regionID).observe(viewLifecycleOwner, {
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