package com.ivestment.doctorna.ui.auth

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.ivestment.doctorna.R
import com.ivestment.doctorna.base.BaseFragment
import com.ivestment.doctorna.utils.phoneValidator
import kotlinx.android.synthetic.main.fragment_check_phone.*

abstract class BasePhoneInput : BaseFragment() {
    lateinit var phoneNumber: String

    override fun getLayout(): Int {
        return R.layout.fragment_check_phone
    }

    abstract val titleID: Int
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title.text = getString(titleID)

        phoneInput.doOnTextChanged { text, _, _, _ ->
            confirmBTN.isEnabled = !text.isNullOrBlank()
        }

        confirmBTN.setOnClickListener {
            validate()
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