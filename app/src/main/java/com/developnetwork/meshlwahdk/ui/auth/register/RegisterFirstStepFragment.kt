package com.developnetwork.meshlwahdk.ui.auth.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.utils.confirmPasswordValidator
import com.developnetwork.meshlwahdk.utils.emailValidator
import com.developnetwork.meshlwahdk.utils.extensions.callUS
import com.developnetwork.meshlwahdk.utils.nameValidator
import com.developnetwork.meshlwahdk.utils.passwordValidator
import kotlinx.android.synthetic.main.fragment_register_first_step.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class RegisterFirstStepFragment : BaseFragment() {

    private val viewModel: RegisterViewModel by sharedViewModel()
    private val args: RegisterFirstStepFragmentArgs by navArgs()

    override fun getLayout(): Int {
        return R.layout.fragment_register_first_step
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueBTN.setOnClickListener {
            validate()
        }
        callBTN.setOnClickListener {
            callUS(requireContext())
        }
    }


    private fun validate() {
        if (nameValidator(nameLayout, requireContext()) &&
            emailValidator(emailLayout, requireContext()) &&
            passwordValidator(passwordLayout, requireContext()) &&
            confirmPasswordValidator(
                conPasswordLayout,
                passwordInput.text.toString(),
                requireContext()
            )
        )
            handleRegister()
    }

    private fun handleRegister() {
        viewModel.name = nameInput.text.toString()
        viewModel.phone = args.phone
        viewModel.email = emailInput.text.toString()
        viewModel.password = passwordInput.text.toString()

        findNavController().navigate(RegisterFirstStepFragmentDirections.actionRegisterFirstStepFragmentToRegisterSecondStepFragment())
    }
}