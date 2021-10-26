package com.developnetwork.meshlwahdk.ui.main.dose.adddose

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
import java.text.SimpleDateFormat
import java.util.*

class AddDoseFragment:BaseDoseInputFragment() {
    private val viewModel:AddDoseViewModel by viewModel()
    override val inputViewModel: BaseDoseInputViewModel
        get() = viewModel

    private val args:AddDoseFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleDate()

    }

    override fun setProducts(list: List<Product>) {
        super.setProducts(list)
        if(args.productID!=0){
            for (i in list.indices) {
                if (args.productID == list[i].id)
                    productSpinner.spinner.setSelection(i)
            }
        }
    }

    private fun handleDate() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val c = Calendar.getInstance()
        c.add(Calendar.DAY_OF_YEAR, 1)

        endDateInput.setOnClickListener {
            openDatePicker(endDateInput, requireContext(),1)
        }
        endDateInput.setText(sdf.format(c.time))
    }
}