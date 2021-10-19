package com.developnetwork.meshlwahdk.ui.auth.register

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.ui.adapters.RegionsAdapter
import com.developnetwork.meshlwahdk.utils.extensions.callUS
import com.developnetwork.meshlwahdk.utils.lengthValidator
import com.developnetwork.meshlwahdk.utils.openDatePicker
import com.developnetwork.meshlwahdk.utils.spinnerValidator
import kotlinx.android.synthetic.main.fragment_register_second_step.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class RegisterSecondStepFragment : BaseFragment() {
    private val viewModel: RegisterViewModel by sharedViewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_register_second_step
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleError(viewModel)
        handleProgress(viewModel)

        handleButtons()
        handleDate()

        handleGender()
        handleCitiesLiveData()
    }

    private fun handleButtons() {
        continueBTN.setOnClickListener {
            validate()
        }

        backBTN.setOnClickListener {
            findNavController().navigateUp()
        }

        callBTN.setOnClickListener {
            callUS(requireContext())
        }
    }

    private fun handleGender() {
        genderSpinner.setAdapter(
            ArrayAdapter(
                requireContext(), android.R.layout.simple_list_item_1,
                arrayOf(getString(R.string.male), getString(R.string.female))
            )
        )
    }

    private fun validate() {
        if (isValidateDateOfBirth()
            && lengthValidator(nationalIDInput, 14, requireContext())
            && spinnerValidator(genderSpinner.getSpinner(), requireContext())
            && spinnerValidator(citySpinner.getSpinner(), requireContext())
            && spinnerValidator(areaSpinner.getSpinner(), requireContext())
        )
            next()
    }

    private fun next() {
        viewModel.nationalId = nationalIDInput.text.toString()
        viewModel.age = viewModel.getAge(birthdateInput.text.toString())
        viewModel.gender = genderSpinner.getSelectedItemId()
        viewModel.region_id = citySpinner.getSelectedItemId()
        viewModel.subRegion_id = areaSpinner.getSelectedItemId()

        findNavController().navigate(RegisterSecondStepFragmentDirections.actionRegisterSecondStepFragmentToSelectProductFragment())
    }

    private fun handleDate() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val c = Calendar.getInstance()
        c.add(Calendar.DAY_OF_YEAR, 1)

        birthdateInput.setOnClickListener {
            openDatePicker(birthdateInput, requireContext())
        }
        birthdateInput.setText(sdf.format(c.time))
    }

    private fun isValidateDateOfBirth(): Boolean {
        val myFormat = "yyMMdd" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val bdSdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        if (nationalIDInput.text.toString()
                .contains(sdf.format(bdSdf.parse(birthdateInput.text.toString())))
        ) {
            return true
        }
        nationalIDInput.error = getString(R.string.please_enter_a_valid_national_id)
        return false
    }

    private fun handleCitiesLiveData() {
        viewModel.getRegions(getString(R.string.city)).observe(viewLifecycleOwner, {
            citySpinner.setAdapter(RegionsAdapter(requireContext(), it))
            citySpinner.onItemSelected {
                if (0 != it)
                    handleAreasLiveData(it)
            }
        })
    }

    private fun handleAreasLiveData(districtID: Int) {
        viewModel.getDistricts(districtID, getString(R.string.area)).observe(viewLifecycleOwner, {
            areaSpinner.setAdapter(RegionsAdapter(requireContext(), it))
        })
    }
}