package com.developnetwork.meshlwahdk.ui.main.dose

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.data.model.Product
import com.developnetwork.meshlwahdk.ui.adapters.ProductsSpinnerAdapter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_base_dose_input.*

abstract class BaseDoseInputFragment : BaseFragment() {
    abstract val inputViewModel: BaseDoseInputViewModel

    override fun getLayout(): Int {
        return R.layout.fragment_base_dose_input
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleError(inputViewModel)
        handleProgress(inputViewModel)

        handleProductsLiveData()

        nextBTN.setOnClickListener {
            save()
        }
    }

    private fun save() {
        inputViewModel.save(
            productSpinner.getSelectedItemId(),
            frequencyInput.text.toString(),
            endDateInput.text.toString()
        ).observe(viewLifecycleOwner, {
            findNavController().navigateUp()
            Toasty.success(requireContext(), R.string.done, 0).show()
        })
    }

    private fun handleProductsLiveData() {
        inputViewModel.getProducts().observe(viewLifecycleOwner, {
            setProducts(it)
        })
    }

    open fun setProducts(list: List<Product>) {
        productSpinner.setAdapter(ProductsSpinnerAdapter(requireContext(), list))

    }


}