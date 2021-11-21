package com.developnetwork.meshlwahdk.ui.main.editprofile

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
import com.developnetwork.meshlwahdk.utils.extensions.setImageURL
import com.developnetwork.meshlwahdk.utils.nameValidator
import com.developnetwork.meshlwahdk.utils.openDatePicker
import com.developnetwork.meshlwahdk.utils.spinnerValidator
import com.ivestment.doctorna.utils.PathUtil
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class EditProfileFragment : BaseFragment() {
    private val viewModel: EditProfileViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_edit_profile
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
                        viewModel.profilePicPath =
                            selectedImage?.let { PathUtil.getPath(requireContext(), it) }
                        profileImage.setImageURI(selectedImage)
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

        handleGetUser()

        handleGender()

        handleDate()

        handleCitiesLiveData()

        saveBTN.setOnClickListener {
            validate()
        }

        editPPBTN.setOnClickListener {
            selectProfilePic()
        }

        profileImage.setOnClickListener {
            selectProfilePic()
        }
    }

    private fun handleDate() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val c = Calendar.getInstance()
        c.add(Calendar.DAY_OF_YEAR, 1)

        birthdateInput.setOnClickListener {
            openDatePicker(birthdateInput, requireContext(), 0)
        }
        birthdateInput.setText(sdf.format(c.time))
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
        if (nameValidator(nameLayout, requireContext())
            && spinnerValidator(citySpinner.getSpinner(), requireContext())
            && spinnerValidator(areaSpinner.getSpinner(), requireContext())
        )
            handleEditProfile()
    }

    private fun handleGetUser() {
        viewModel.getUser().observe(viewLifecycleOwner, {
            nameInput.setText(it.name)

            if (!it.img.isNullOrBlank())
                profileImage.setImageURL(it.img)
        })
    }

    private fun handleEditProfile() {
        viewModel.editProfile(
            nameInput.text.toString(),
            genderSpinner.getSelectedItemId().toString(),
            citySpinner.getSelectedItemId(),
            areaSpinner.getSelectedItemId(),
            birthdateInput.text.toString()
        ).observe(viewLifecycleOwner, {
            Toasty.success(requireContext(), R.string.done, 0).show()
            findNavController().navigateUp()
        })
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
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun openPhotoGallery() {
        val galleryIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.setType("image/*")

        resultLauncher.launch(galleryIntent)
    }

}