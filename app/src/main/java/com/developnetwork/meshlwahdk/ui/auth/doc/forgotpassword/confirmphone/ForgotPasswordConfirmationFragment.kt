package com.ivestment.doctorna.ui.auth.forgotpassword.confirmphone

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ivestment.doctorna.ui.auth.BaseConfirmPhoneFragment
import com.ivestment.doctorna.ui.auth.BaseConfirmPhoneViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordConfirmationFragment:BaseConfirmPhoneFragment() {
    private val vm :ForgotPasswordConfirmationViewModel by viewModel()
    private val args:ForgotPasswordConfirmationFragmentArgs by navArgs()

    override val viewModel: BaseConfirmPhoneViewModel
        get() = vm
    override val phone: String
        get() = args.phone

    override fun confirmed() {
findNavController().navigate(ForgotPasswordConfirmationFragmentDirections.actionForgotPasswordConfirmationFragmentToResetPasswordFragment(phone))
    }
}