package com.ivestment.doctorna.ui.auth.register.checkphone

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.ivestment.doctorna.R
import com.ivestment.doctorna.base.BaseFragment
import com.ivestment.doctorna.ui.auth.BasePhoneInput
import com.ivestment.doctorna.utils.phoneValidator
import kotlinx.android.synthetic.main.fragment_check_phone.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckPhoneFragment : BasePhoneInput() {
    private val viewModel: CheckPhoneViewModel by viewModel()

    override val titleID: Int
        get() = R.string.register

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleError(viewModel)
        handleProgress(viewModel)
    }

    override fun check() {
        handleCheckPhone()
    }

    private fun handleCheckPhone() {
        viewModel.checkPhone(phoneNumber).observe(viewLifecycleOwner, {
            if (it) {
                handlePhoneRegister()
            } else {
                handleGetUser()
            }
        })
    }

    private fun handleGetUser() {
        viewModel.getUser(phoneNumber).observe(viewLifecycleOwner, {
            if (it.phoneVerifiedAt.isNullOrBlank()) {
                findNavController().navigate(
                    CheckPhoneFragmentDirections.actionRegisterStepZeroFragmentToConfirmNumberFragment(
                        phoneNumber
                    )
                )
            } else if (it.name.isNullOrBlank()) {
                findNavController().navigate(
                    CheckPhoneFragmentDirections.actionRegisterStepZeroFragmentToRegisterStep1Fragment(
                        phoneNumber
                    )
                )
            } else {
                findNavController().navigate(
                    CheckPhoneFragmentDirections.actionRegisterStepZeroFragmentToLoginFragment(
                        phoneInput.text.toString()
                    )
                )
            }
        })
    }

    private fun handlePhoneRegister() {
        viewModel.registerPhone(phoneNumber).observe(viewLifecycleOwner, {
            findNavController().navigate(
                CheckPhoneFragmentDirections.actionRegisterStepZeroFragmentToConfirmNumberFragment(
                    phoneNumber
                )
            )
        })
    }
}