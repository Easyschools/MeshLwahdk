package com.developnetwork.meshlwahdk.ui.auth.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.utils.*
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class RegisterFragment : BaseFragment() {

    private val viewModel: RegisterViewModel by viewModel()
    private val args: RegisterFragmentArgs by navArgs()

    override fun getLayout(): Int {
        return R.layout.fragment_register
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueBTN.setOnClickListener {
            validate()
        }

        tv_register_login.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        handleDate()
    }

    private fun handleDate() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val c = Calendar.getInstance()
        c.add(Calendar.DAY_OF_YEAR, 1)

        dateOfBirthInput.setOnClickListener {
            openDatePicker(dateOfBirthInput, requireContext())
        }

        dateOfBirthInput.text = sdf.format(c.time)
    }

    private fun validate() {
        if (nameValidator(nameInput, requireContext()) &&
            emailValidator(emailInput, requireContext()) &&
            isValidateDateOfBirth() &&
            lengthValidator(nationalIdInput, 14, requireContext()) &&
            passwordValidator(passwordInput, requireContext()) &&
            isGender()
        )
            handleRegister()
    }

    private fun handleRegister() {
        viewModel.name = nameInput.text.toString()
        viewModel.phone = args.phone
        viewModel.email = emailInput.text.toString()
        viewModel.age = viewModel.getAge(dateOfBirthInput.text.toString())
        viewModel.nationalId = nationalIdInput.text.toString()
        viewModel.password = passwordInput.text.toString()
        viewModel.gender = if (genderRadioGroup.checkedRadioButtonId == R.id.maleRadio)
            0 else 1

        viewModel.completeRegister().observe(viewLifecycleOwner, {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToMainActivity())
        })
    }

    private fun isValidateDateOfBirth(): Boolean {
        val myFormat = "yyMMdd" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val bdSdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        if (nationalIdInput.text.toString()
                .contains(sdf.format(bdSdf.parse(dateOfBirthInput.text.toString())))
        ) {
            return true
        }
        nationalIdInput.error = getString(R.string.please_enter_a_valid_national_id)
        return false
    }

    private fun isGender(): Boolean {
        if (genderRadioGroup.checkedRadioButtonId == null) {
//        if (gender == "") {
            Toast.makeText(
                requireContext(),
                getString(R.string.please_select_the_gender),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
}