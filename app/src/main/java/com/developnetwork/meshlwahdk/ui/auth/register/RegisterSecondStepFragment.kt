package com.developnetwork.meshlwahdk.ui.auth.register

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.ui.adapters.RegionsAdapter
import com.developnetwork.meshlwahdk.utils.extensions.callUS
import com.developnetwork.meshlwahdk.utils.lengthValidator
import com.developnetwork.meshlwahdk.utils.openDatePicker
import com.developnetwork.meshlwahdk.utils.spinnerValidator
import com.ivestment.doctorna.utils.PathUtil
import kotlinx.android.synthetic.main.fragment_register_second_step.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class RegisterSecondStepFragment : BaseFragment() {
    private val viewModel: RegisterViewModel by sharedViewModel()
    private var selectedPath = -1

    override fun getLayout(): Int {
        return R.layout.fragment_register_second_step
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                openPhotoGallery()
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data

                data?.let { data ->
                    val selectedImage: Uri? = data.data
                    try {
                        when (selectedPath) {
                            0 -> {
                                viewModel.profilePicPath =
                                    selectedImage?.let { PathUtil.getPath(requireContext(), it) }
                                uploadPhotoBTN.text = getString(R.string.image_selected)
                            }
                            1 -> {
                                viewModel.identityCardImagePath =
                                    selectedImage?.let { PathUtil.getPath(requireContext(), it) }
                                uploadIDBTN.text = getString(R.string.image_selected)
                            }
                        }

                        selectedPath = -1
                    } catch (e: Exception) {
                        Timber.tag("image_path").e(e)
                    }
                }
            }
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

        uploadIDBTN.setOnClickListener {
            selectedPath = 1
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        uploadPhotoBTN.setOnClickListener {
            selectProfilePic()
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
            openDatePicker(birthdateInput, requireContext(),0)
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
    private fun selectProfilePic() {
        selectedPath = 0
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }
    private fun openPhotoGallery() {
        val galleryIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.setType("image/*")

        resultLauncher.launch(galleryIntent)
    }
}