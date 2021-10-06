package com.ivestment.doctorna.ui.auth.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ivestment.doctorna.R
import com.ivestment.doctorna.base.BaseFragment
import com.ivestment.doctorna.utils.*
import kotlinx.android.synthetic.main.fragment_register_step1.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class RegisterStep1Fragment : BaseFragment() {

    private val viewModel: RegisterViewModel by sharedViewModel()
    private val args: RegisterStep1FragmentArgs by navArgs()

    override fun getLayout(): Int {
        return R.layout.fragment_register_step1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleDate()

        handleButtonClick()
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


    private fun handleButtonClick() {

        continueBTN.setOnClickListener {
            validate()
        }

        loginBTN.setOnClickListener {
            findNavController().popBackStack(R.id.loginFragment, false)
        }
    }

    private fun validate() {
        if (nameValidator(nameInput, requireContext()) &&
            emailValidator(emailInput, requireContext()) &&
            isValidateDateOfBirth() &&
            lengthValidator(nationalIdInput, 14, requireContext()) &&
            passwordValidator(passwordInput, requireContext()) &&
            isGender()
        )
            saveDate()
    }


    private fun saveDate() {
        viewModel.name = nameInput.text.toString()
        viewModel.phone = args.phone
        viewModel.email = emailInput.text.toString()
        viewModel.age = viewModel.getAge(dateOfBirthInput.text.toString())
        viewModel.nationalId = nationalIdInput.text.toString()
        viewModel.password = passwordInput.text.toString()
        viewModel.gender = if (genderRadioGroup.checkedRadioButtonId == R.id.maleRadio)
            0 else 1
        findNavController().navigate(RegisterStep1FragmentDirections.actionRegisterStep1FragmentToRegisterStep2Fragment())
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