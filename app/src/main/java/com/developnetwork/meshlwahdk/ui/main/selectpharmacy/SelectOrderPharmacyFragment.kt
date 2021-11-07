package com.developnetwork.meshlwahdk.ui.main.selectpharmacy

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_pharmacies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectOrderPharmacyFragment : BaseFragment() {
    private lateinit var adapter: SelectOrdersAdapter
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
        handlePharmaciesLiveData()
    }

    private fun initList() {
        adapter = SelectOrdersAdapter() { pharmacyId, name ->
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

    private fun handlePharmaciesLiveData() {
        viewModel.getPharmacies(args.programID).observe(viewLifecycleOwner, {
            adapter.submitList(it.toMutableList())
        })
    }

}