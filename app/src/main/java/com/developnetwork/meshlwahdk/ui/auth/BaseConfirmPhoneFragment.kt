package com.developnetwork.meshlwahdk.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseAutoVerify
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.utils.extensions.callUS
import kotlinx.android.synthetic.main.fragment_confirm_number.*

abstract class BaseConfirmPhoneFragment : BaseFragment() {
    abstract val viewModel: BaseConfirmPhoneViewModel
    abstract val phone: String
    private val baseAutoVerify = BaseAutoVerify(::requestActivityForResult, ::setCode)
    private lateinit var timer: CountDownTimer
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

        phone_text.text = "${getString(R.string.verification_code_has_been_sent_to)} ${phone}"

        confirmBtn.setOnClickListener {
            handleConfirmPhone()
        }
        resendBTN.setOnClickListener {
            timer.cancel()
            handleResend()
        }
        callBTN.setOnClickListener {
            callUS(requireContext())
        }
        backBTN.setOnClickListener {
            findNavController().navigateUp()
        }
        initTimer()
        timer.start()
    }

    abstract fun confirmed()

    private fun handleConfirmPhone() {
        viewModel.confirm(phone, pinInput.text.toString()).observe(viewLifecycleOwner, {
            confirmed()
        })
    }

    private fun handleResend() {
        viewModel.resend(phone).observe(viewLifecycleOwner, {
            resendBTN.isEnabled = false
            timer.start()
        })
    }


    private fun initTimer() {
        timer = object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                counter.text = "${minutes % 60}:${seconds % 60}"
            }

            override fun onFinish() {
                counter.text = "0:00"
                resendBTN.isEnabled = true
            }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
    }
}