package com.developnetwork.meshlwahdk.ui.auth.register

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.ui.auth.BasePhoneInput
import kotlinx.android.synthetic.main.fragment_register_first_step.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFirstStepFragment  : BasePhoneInput() {
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
                    RegisterFirstStepFragmentDirections.actionRegisterFirstStepFragmentToConfirmNumberFragment(
                        phoneNumber
                    )
                )
            }
            else if (it.name.isNullOrBlank()) {
                findNavController().navigate(
                    RegisterFirstStepFragmentDirections.actionRegisterFirstStepFragmentToRegisterFragment(
                        phoneNumber
                    )
                )
            }
//            else {
//                findNavController().navigate(
//                    CheckPhoneFragmentDirections.actionRegisterStepZeroFragmentToLoginFragment(
//                        phoneInput.text.toString()
//                    )
//                )
//            }
        })
    }

    private fun handlePhoneRegister() {
        viewModel.registerPhone(phoneNumber).observe(viewLifecycleOwner, {
            findNavController().navigate(
                RegisterFirstStepFragmentDirections.actionRegisterFirstStepFragmentToConfirmNumberFragment(phoneNumber)
            )
        })
    }
}