package com.developnetwork.meshlwahdk.ui.main.dose.editdose

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.data.model.Product
import com.developnetwork.meshlwahdk.ui.main.dose.BaseDoseInputFragment
import com.developnetwork.meshlwahdk.ui.main.dose.BaseDoseInputViewModel
import com.developnetwork.meshlwahdk.utils.openDatePicker
import kotlinx.android.synthetic.main.fragment_base_dose_input.*
import kotlinx.android.synthetic.main.meshlwahdk_spinner.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditDoseFragment : BaseDoseInputFragment() {
    private val viewModel: EditDoseViewModel by viewModel()
    override val inputViewModel: BaseDoseInputViewModel
        get() = viewModel

    private val args: EditDoseFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.dose=args.dose
        super.onViewCreated(view, savedInstanceState)
        handleDate()

        frequencyInput.setText(viewModel.dose.frequency)
    }

    override fun setProducts(list: List<Product>) {
        super.setProducts(list)

        for (i in list.indices) {
            if (viewModel.dose.productId == list[i].id)
                productSpinner.spinner.setSelection(i)
        }

    }


    private fun handleDate() {
        endDateInput.setOnClickListener {
            openDatePicker(endDateInput, requireContext(), 1)
        }
        endDateInput.setText(viewModel.dose.duration)
    }
}