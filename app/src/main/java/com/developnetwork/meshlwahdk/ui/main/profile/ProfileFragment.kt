package com.developnetwork.meshlwahdk.ui.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.ui.auth.AuthActivity
import com.developnetwork.meshlwahdk.ui.dialogs.ChangeLanguageDialog
import com.developnetwork.meshlwahdk.utils.extensions.setImageURL
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment() {
    private val viewModel: ProfileViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleUserData()
        handleButtons()
    }

    private fun handleUserData() {
        val user = viewModel.sharedPreferencesManager.userData
        if (!user.img.isNullOrBlank())
            profileImage.setImageURL(user.img)

        name.text = user.name
    }

    private fun handleButtons() {
        editProfileBTN.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment())
        }
        dosesBTN.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToDoseListFragment())
        }
        resetPasswordBTN.setOnClickListener {
            resetPassword()
        }
        editPhoneBTN.setOnClickListener {
            Toasty.info(requireContext(), "under development", 0).show()
        }
        languageBTN.setOnClickListener {
            ChangeLanguageDialog().show(childFragmentManager, "change_language")
        }
        logoutBTN.setOnClickListener {
            logout()
        }
    }


    private fun logout() {
        viewModel.sharedPreferencesManager.clearData()
        viewModel.sharedPreferencesManager.isLoggedIn = false

        val i = Intent(requireContext(), AuthActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(i)
    }

    private fun resetPassword() {
        viewModel.forgotPassword(viewModel.sharedPreferencesManager.userPhone)
            .observe(viewLifecycleOwner, {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToForgotPasswordConfirmationFragment2(
                        viewModel.sharedPreferencesManager.userPhone
                    )
                )
            })
    }

}