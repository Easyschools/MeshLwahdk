package com.developnetwork.meshlwahdk.ui.auth.forgotpassword

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.ui.auth.BasePhoneInput
import kotlinx.android.synthetic.main.fragment_phone_input.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordFragment : BasePhoneInput() {
    private val viewModel: ForgotPasswordViewModel by viewModel()
    override val titleID: Int
        get() = R.string.forget_password

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title2.visibility=View.INVISIBLE

        handleProgress(viewModel)
        handleError(viewModel)
    }

    override fun check() {
        viewModel.forgotPassword(phoneNumber).observe(viewLifecycleOwner, {
            findNavController().navigate(
                ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToForgotPasswordConfirmationFragment(
                    phoneNumber
                )
            )
        })
    }
}