package com.ivestment.doctorna.ui.auth.forgotpassword.resetpassword

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ivestment.doctorna.R
import com.ivestment.doctorna.base.BaseFragment
import com.ivestment.doctorna.utils.confirmPasswordValidator
import com.ivestment.doctorna.utils.passwordValidator
import kotlinx.android.synthetic.main.fragment_reset_password.*
import kotlinx.android.synthetic.main.fragment_reset_password.passwordInput
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResetPasswordFragment : BaseFragment() {
    private val viewModel: ResetPasswordViewModel by viewModel()
    private val args: ResetPasswordFragmentArgs by navArgs()

    override fun getLayout(): Int {
        return R.layout.fragment_reset_password
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleError(viewModel)
        handleProgress(viewModel)

        confirmBTN.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        if (passwordValidator(passwordInput, requireContext()) && confirmPasswordValidator(
                conPasswordInput,
                passwordInput.text.toString(),
                requireContext()
            )
        )
            handleResetPassword()
    }

    private fun handleResetPassword() {
        viewModel.resetPassword(args.phone, passwordInput.text.toString())
            .observe(viewLifecycleOwner, {
                findNavController().popBackStack(R.id.loginFragment, false)
            })
    }
}