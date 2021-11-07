package com.developnetwork.meshlwahdk.ui.main.changephone.confirm

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.ui.auth.BaseConfirmPhoneFragment
import com.developnetwork.meshlwahdk.ui.auth.BaseConfirmPhoneViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfirmUpdatedPhoneFragment : BaseConfirmPhoneFragment() {
    private val confirmUpdatedPhoneViewModel: ConfirmUpdatedPhoneViewModel by viewModel()
    private val args: ConfirmUpdatedPhoneFragmentArgs by navArgs()

    override val viewModel: BaseConfirmPhoneViewModel
        get() = confirmUpdatedPhoneViewModel
    override val phone: String
        get() = args.phone

    override fun confirmed() {
        findNavController().popBackStack(R.id.profileFragment, false)
    }
}