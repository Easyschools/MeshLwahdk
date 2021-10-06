package com.ivestment.doctorna.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ivestment.doctorna.MainActivity
import com.ivestment.doctorna.R
import com.ivestment.doctorna.base.BaseFragment
import com.ivestment.doctorna.utils.passwordValidator
import com.ivestment.doctorna.utils.phoneValidator
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var phoneNumber: String

    override fun getLayout(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleError(viewModel)
        handleProgress(viewModel)

        handleButtons()
    }

    private fun handleButtons() {
        loginBTN.setOnClickListener {
            validate()
        }

        forgotPasswordBTN.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }

        registerBTN.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterStepZeroFragment())
        }
    }

    private fun validate() {
        if (phoneValidator(phoneInput, requireContext()) && passwordValidator(
                passwordInput,
                requireContext()
            )
        ) {
            setPhoneNumber()
            handleLogin()
        }
    }

    private fun handleLogin() {
        viewModel.userLogIn(phoneNumber, passwordInput.text.toString())
            .observe(viewLifecycleOwner, {

                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finishAffinity()
            }
            )
    }

    private fun setPhoneNumber() {
        phoneNumber = phoneInput.text.toString()

        if (phoneNumber.contains("+2"))
            phoneNumber = phoneNumber.removePrefix("+2")
    }
}