package com.developnetwork.meshlwahdk.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_confirm_number.*


class ConfirmNumberFragment : BaseFragment() {
//    private val viewModel: ConfirmNumberViewModel by viewModel()
//    private val args: ConfirmNumberFragmentArgs by navArgs()
//    private val baseAutoVerify = BaseAutoVerify(::requestActivityForResult, ::setCode)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        activity?.let {
//            baseAutoVerify.listenToSms(it, it)
//        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_confirm_number
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        handleProgress(viewModel)
//        handleError(viewModel)

//        phone_text.text = getString(R.string.code_has_been_sent_to) + args.phone

//        confirmBtn.setOnClickListener {
//            if (!pinInput.isError) {
//                if (args.toResetPassword)
//                    checkCodeLiveData()
//                else
//                    handleConfirmLiveData()
//            }
//        }
//        pinInput.setOnPinEnteredListener {
//            handleConfirmLiveData()
//        }
//        registerBTN.setOnClickListener {
//            findNavController().navigate(R.id.registerFragment)
//        }
//        resendBTN.setOnClickListener {
//            resendCode()
//        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        baseAutoVerify.onVerifyResultHandler(requestCode, resultCode, data)
    }

    private fun handleConfirmLiveData() {
//        viewModel.confirmPhone(pinInput.text.toString()).observe(viewLifecycleOwner, {
//            val user = CarVisetaApplication.sharedPreferencesManager.userData
//            user.phoneVerifiedAt = "xx"
//            CarVisetaApplication.sharedPreferencesManager.userData = user
//            gotoHome()
//        })
    }

    private fun checkCodeLiveData() {
//        viewModel.checkCode(args.phone, pinInput.text.toString()).observe(viewLifecycleOwner, {
//            findNavController().navigate(
//                ConfirmNumberFragmentDirections.actionConfirmNumberFragmentToResetPasswordFragment(
//                    args.phone,
//                    pinInput.text.toString()
//                )
//            )
//        })
    }

    private fun resendCode() {
//        viewModel.resendPhone(args.phone).observe(viewLifecycleOwner, {})
    }

    private fun gotoHome() {
//        val intent = Intent(requireContext(), MainTabsActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//        startActivity(intent)
//        requireActivity().finishAffinity()
    }

    private fun requestActivityForResult(consentIntent: Intent?, requestCode: Int) {
        if (activity != null)
            startActivityForResult(consentIntent, requestCode)
    }

    private fun setCode(code: String) {
        pinInput.setText(code)
    }

}