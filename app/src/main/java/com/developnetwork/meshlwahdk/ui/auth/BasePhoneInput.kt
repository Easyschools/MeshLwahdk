package com.developnetwork.meshlwahdk.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.utils.extensions.callUS
import com.developnetwork.meshlwahdk.utils.phoneValidator
import kotlinx.android.synthetic.main.fragment_phone_input.*

abstract class BasePhoneInput : BaseFragment() {
    lateinit var phoneNumber: String

    override fun getLayout(): Int {
        return R.layout.fragment_phone_input
    }

    abstract val titleID: Int
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countriesSpinner.setAdapter(
            ArrayAdapter(
                requireContext(), android.R.layout.simple_list_item_1,
                arrayOf("Egypt (+2)")
            )
        )

        phoneInput.doOnTextChanged { text, _, _, _ ->
            getCodeBTN.isEnabled = !text.isNullOrBlank()
        }

        getCodeBTN.setOnClickListener {
            validate()
        }

        callBTN.setOnClickListener {
            callUS(requireContext())
        }
    }

    private fun validate() {
        if (phoneValidator(phoneInput, requireContext())) {
            setPhoneNumber()
            check()
        }
    }

    abstract fun check()

    private fun setPhoneNumber() {
        phoneNumber = phoneInput.text.toString()

        if (phoneNumber.contains("+2"))
            phoneNumber = phoneNumber.removePrefix("+2")
    }
}