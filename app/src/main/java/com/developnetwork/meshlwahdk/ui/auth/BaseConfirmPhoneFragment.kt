package com.developnetwork.meshlwahdk.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseAutoVerify
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_confirm_number.*

abstract class BaseConfirmPhoneFragment : BaseFragment() {
    abstract val viewModel: BaseConfirmPhoneViewModel
abstract val phone:String
private val baseAutoVerify = BaseAutoVerify(::requestActivityForResult, ::setCode)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            baseAutoVerify.listenToSms(it, it)
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_confirm_number
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleError(viewModel)
        handleProgress(viewModel)

        phone_text.text="${getString(R.string.sms_has_been_sent_to)} ${phone}"

        confirmBtn.setOnClickListener {
            handleConfirmPhone()
        }
        resendBTN.setOnClickListener {
            handleResend()
        }
    }

    abstract fun confirmed()
    
    private fun handleConfirmPhone() {
        viewModel.confirm(phone, pinInput.text.toString()).observe(viewLifecycleOwner, {
            confirmed()
        })
    }

    private fun handleResend(){
        viewModel.resend(phone).observe(viewLifecycleOwner,{
        })
    }

    
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        baseAutoVerify.onVerifyResultHandler(requestCode, resultCode, data)
    }

    private fun requestActivityForResult(consentIntent: Intent?, requestCode: Int) {
        if (activity != null)
            startActivityForResult(consentIntent, requestCode)
    }

    private fun setCode(code: String) {
        pinInput.setText(code)
    }

}