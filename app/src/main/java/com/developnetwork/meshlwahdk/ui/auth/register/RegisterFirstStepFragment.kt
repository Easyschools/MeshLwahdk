package com.developnetwork.meshlwahdk.ui.auth.register

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register_first_step.*

class RegisterFirstStepFragment : BaseFragment() {
    override fun getLayout(): Int {
        return R.layout.fragment_register_first_step
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countriesSpinner.setAdapter(
            ArrayAdapter(
                requireContext(), android.R.layout.simple_list_item_1,
                arrayOf("Egypt (+2)")
            )
        )


    }
}