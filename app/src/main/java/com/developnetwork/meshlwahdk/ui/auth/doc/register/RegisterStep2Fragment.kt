package com.ivestment.doctorna.ui.auth.register

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.ivestment.doctorna.MainActivity
import com.ivestment.doctorna.R
import com.ivestment.doctorna.adapters.spinners.PatientCategoryAdapter
import com.ivestment.doctorna.adapters.spinners.RegionsAdapter
import com.ivestment.doctorna.base.BaseFragment
import com.ivestment.doctorna.utils.PathUtil
import com.ivestment.doctorna.utils.spinnerValidator
import kotlinx.android.synthetic.main.fragment_register_step2.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RegisterStep2Fragment : BaseFragment() {

    private val viewModel: RegisterViewModel by sharedViewModel()
    private var selectedPath = -1

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
                                image.setImageURI(selectedImage)

                            }
                            1 -> {
                                viewModel.insuranceCardImagePath =
                                    selectedImage?.let { PathUtil.getPath(requireContext(), it) }
                                insuranceCardInput.text = getString(R.string.image_selected)

                            }
                            2 -> {
                                viewModel.identityCardImagePath =
                                    selectedImage?.let { PathUtil.getPath(requireContext(), it) }
                                identityCardInput.text = getString(R.string.image_selected)
                            }
                            3 -> {
                                viewModel.categoryDocumentPath =
                                    selectedImage?.let { PathUtil.getPath(requireContext(), it) }
                                categoryInput.text = getString(R.string.image_selected)
                            }
                        }

                        selectedPath = -1
                    } catch (e: Exception) {
                        Timber.tag("image_path").e(e)
                    }
                }
            }
        }

    override fun getLayout(): Int {
        return R.layout.fragment_register_step2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleError(viewModel)
        handleProgress(viewModel)

        handleRegionsLiveData()
        handleCategoriesLiveData()

        registerBTN.setOnClickListener {
            validate()
        }

        image.setOnClickListener {
            selectProfilePic()
        }
        imageBTN.setOnClickListener {
            selectProfilePic()
        }
        insuranceCardBTN.setOnClickListener {
            selectedPath = 1
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        identityCardBTN.setOnClickListener {
            selectedPath = 2
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        categoryBTN.setOnClickListener {
            selectedPath = 3
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun selectProfilePic() {
        selectedPath = 0
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun handleCategoriesLiveData() {
        viewModel.getCategories(getString(R.string.category)).observe(viewLifecycleOwner, {
            categorySpinner.setAdapter(PatientCategoryAdapter(requireContext(), it))
        })
    }

    private fun handleRegionsLiveData() {
        viewModel.getRegions(getString(R.string.government)).observe(viewLifecycleOwner, {
            governmentSpinner.setAdapter(RegionsAdapter(requireContext(), it))
            governmentSpinner.onItemSelected {
                if (0 != it)
                    handleDistrictsLiveData(it)
            }
        })
    }

    private fun handleDistrictsLiveData(regionID: Int) {
        viewModel.getDistricts(regionID, getString(R.string.city)).observe(viewLifecycleOwner, {
            citySpinner.setAdapter(RegionsAdapter(requireContext(), it))
            citySpinner.onItemSelected {
                if (0 != it)
                    handleSubRegionsLiveData(it)
            }
        })
    }

    private fun handleSubRegionsLiveData(districtID: Int) {
        viewModel.getSubRegions(districtID, getString(R.string.area)).observe(viewLifecycleOwner, {
            areaSpinner.setAdapter(RegionsAdapter(requireContext(), it))
        })
    }


    private fun validate() {
        if (spinnerValidator(governmentSpinner.getSpinner(), requireContext()) && spinnerValidator(
                citySpinner.getSpinner(),
                requireContext()
            ) && spinnerValidator(areaSpinner.getSpinner(), requireContext())
        )
            handleRegister()
    }

    private fun handleRegister() {
        viewModel.completeRegister(
            governmentSpinner.getSelectedItemId(),
            citySpinner.getSelectedItemId(),
            areaSpinner.getSelectedItemId(),
            healthInsuranceInput.text.toString(),
            categorySpinner.getSelectedItemId()
        ).observe(viewLifecycleOwner, {
            viewModel.sharedPreferencesManager.saveUserData(it)
            startActivity(Intent(requireContext(), MainActivity::class.java))

        })
    }

    private fun openPhotoGallery() {
        val galleryIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.setType("image/*")

        resultLauncher.launch(galleryIntent)
    }
}