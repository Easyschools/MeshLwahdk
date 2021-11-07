package com.developnetwork.meshlwahdk.ui.main.changephone

import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.ui.auth.BasePhoneInput
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePhoneNumber : BasePhoneInput() {
    private val viewModel: ChangePhoneViewModel by viewModel()
    override val titleID: Int
        get() = R.string.edit_mobile_number

    override fun check() {
        viewModel.updatePhone(phoneNumber).observe(viewLifecycleOwner, {

        })
    }
}