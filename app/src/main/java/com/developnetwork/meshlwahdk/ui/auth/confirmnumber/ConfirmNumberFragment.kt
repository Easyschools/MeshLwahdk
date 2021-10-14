package com.developnetwork.meshlwahdk.ui.auth.confirmnumber

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developnetwork.meshlwahdk.ui.auth.BaseConfirmPhoneFragment
import com.developnetwork.meshlwahdk.ui.auth.BaseConfirmPhoneViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfirmNumberFragment : BaseConfirmPhoneFragment() {
    private val vm: ConfirmNumberViewModel by viewModel()
    private val args: ConfirmNumberFragmentArgs by navArgs()

    override val viewModel: BaseConfirmPhoneViewModel
        get() = vm
    override val phone: String
        get() = args.phone

    override fun confirmed() {
        findNavController().navigate(
            ConfirmNumberFragmentDirections.actionConfirmNumberFragmentToRegisterFragment(
                phone
            )
        )
    }
}