package com.developnetwork.meshlwahdk.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.utils.extensions.callUS
import com.developnetwork.meshlwahdk.utils.passwordValidator
import com.developnetwork.meshlwahdk.utils.phoneValidator
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

        loginBTN.setOnClickListener {
            validate()
        }

        registerBTN.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterPhoneStepFragment(null))
        }
        forgotPasswordBTN.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }

        callBTN.setOnClickListener {
            callUS(requireContext())
        }
        backBTN.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun validate() {
        if (phoneValidator(phoneInput, requireContext()) && passwordValidator(
                passwordInput,
                requireContext()
            )
        ) {
            setPhoneNumber()
            checkUser()
        }
    }

    private fun checkUser(){
        viewModel.getUser(phoneNumber).observe(viewLifecycleOwner,{
            if(it.name.isNullOrBlank()){
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterPhoneStepFragment(phoneNumber))
                showError(R.string.please_complete_your_registration)
            }else
                handleLogin()
        })
    }

    private fun handleLogin() {
        viewModel.userLogIn(phoneNumber, passwordInput.text.toString())
            .observe(viewLifecycleOwner, {

                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainActivity())
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