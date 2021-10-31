package com.developnetwork.meshlwahdk.ui.auth.forgotpassword.confirmphone

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.ui.auth.BaseConfirmPhoneFragment
import com.developnetwork.meshlwahdk.ui.auth.BaseConfirmPhoneViewModel
import kotlinx.android.synthetic.main.fragment_confirm_number.*

import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordConfirmationFragment : BaseConfirmPhoneFragment() {
    private val vm: ForgotPasswordConfirmationViewModel by viewModel()
    private val args: ForgotPasswordConfirmationFragmentArgs by navArgs()

    override val viewModel: BaseConfirmPhoneViewModel
        get() = vm
    override val phone: String
        get() = args.phone

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(vm.sharedPreferencesManager.isLoggedIn)
            backBTN.visibility=View.GONE
    }

    override fun confirmed() {
        val navigationID =
            if (vm.sharedPreferencesManager.isLoggedIn) R.id.resetPasswordFragment2 else R.id.resetPasswordFragment
        findNavController().navigate(navigationID, Bundle().apply { putString("phone", phone) })
    }
}
