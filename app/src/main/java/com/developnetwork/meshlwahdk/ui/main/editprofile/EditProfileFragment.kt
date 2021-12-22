package com.developnetwork.meshlwahdk.ui.main.editprofile

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.ui.adapters.RegionsAdapter
import com.developnetwork.meshlwahdk.ui.dialogs.UploadImageDialog
import com.developnetwork.meshlwahdk.utils.extensions.setImageURL
import com.developnetwork.meshlwahdk.utils.nameValidator
import com.developnetwork.meshlwahdk.utils.openDatePicker
import com.developnetwork.meshlwahdk.utils.spinnerValidator
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class EditProfileFragment : BaseFragment() {
    private val viewModel: EditProfileViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_edit_profile
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

        UploadImageDialog(getString(R.string.upload_profile_photo)) {
            viewModel.profilePicPath = it
            profileImage.setImageURI(Uri.parse(it))
        }.show(childFragmentManager, "p-p")
    }

}