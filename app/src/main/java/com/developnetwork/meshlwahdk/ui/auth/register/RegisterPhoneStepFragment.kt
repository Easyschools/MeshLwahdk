package com.developnetwork.meshlwahdk.ui.auth.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.ui.auth.BasePhoneInput
import kotlinx.android.synthetic.main.fragment_phone_input.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterPhoneStepFragment : BasePhoneInput() {
    private val viewModel: CheckPhoneViewModel by viewModel()
    private val args: RegisterPhoneStepFragmentArgs by navArgs()

    override val titleID: Int
        get() = R.string.register

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleError(viewModel)
        handleProgress(viewModel)

        if (!args.phone.isNullOrBlank()) {
            phoneInput.setText(args.phone)
            validate()
        }
    }

    override fun check() {
        handleCheckPhone()
    }

    private fun handleCheckPhone() {
        viewModel.checkPhone(phoneNumber).observe(viewLifecycleOwner, {
            if (it.data) {
                handlePhoneRegister()
            } else {
                it.message?.let { msg ->
                    showError(msg)
                }
                handleGetUser()
            }
        })
    }

    private fun handleGetUser() {
        viewModel.getUser(phoneNumber).observe(viewLifecycleOwner, {
            if (it.phoneVerifiedAt.isNullOrBlank()) {
                findNavController().navigate(
                    RegisterPhoneStepFragmentDirections.actionRegisterPhoneStepFragmentToConfirmNumberFragment(
                        phoneNumber
                    )
                )
            } else if (it.name.isNullOrBlank()) {
                findNavController().navigate(
                    RegisterPhoneStepFragmentDirections.actionRegisterPhoneStepFragmentToRegisterFirstStepFragment(
                        phoneNumber
                    )
                )
            } else {
                findNavController().navigate(
                    RegisterPhoneStepFragmentDirections.actionRegisterPhoneStepFragmentToLoginFragment()
                )
            }
        })
    }

    private fun handlePhoneRegister() {
        viewModel.registerPhone(phoneNumber).observe(viewLifecycleOwner, {
            findNavController().navigate(
                RegisterPhoneStepFragmentDirections.actionRegisterPhoneStepFragmentToConfirmNumberFragment(
                    phoneNumber
                )
            )
        })
    }
}