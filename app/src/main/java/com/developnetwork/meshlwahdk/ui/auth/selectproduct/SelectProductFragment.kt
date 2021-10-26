package com.developnetwork.meshlwahdk.ui.auth.selectproduct

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.data.model.Product
import com.developnetwork.meshlwahdk.ui.auth.register.RegisterViewModel
import com.developnetwork.meshlwahdk.utils.extensions.callUS
import kotlinx.android.synthetic.main.fragment_select_product.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SelectProductFragment : BaseFragment() {
    private val viewModel: RegisterViewModel by sharedViewModel()

    lateinit var adapter: ProductsAdapter
    override fun getLayout(): Int {
        return R.layout.fragment_select_product
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleError(viewModel)
        handleProgress(viewModel)

        handleProductsLiveData()

        doneBTN.setOnClickListener {
            if (adapter.selectedProductID != -1)
                viewModel.completeRegister(productID = adapter.selectedProductID).observe(viewLifecycleOwner, {
                    findNavController().navigate(SelectProductFragmentDirections.actionSelectProductFragmentToMainActivity())
                })
        }

        callBTN.setOnClickListener {
            callUS(requireContext())
        }
        backBTN.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initList(list: List<Product>) {
        productsListRV.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductsAdapter(list)
        productsListRV.adapter = adapter
    }

    private fun handleProductsLiveData() {
        viewModel.getProducts().observe(viewLifecycleOwner, {
            initList(it)
        })
    }
}