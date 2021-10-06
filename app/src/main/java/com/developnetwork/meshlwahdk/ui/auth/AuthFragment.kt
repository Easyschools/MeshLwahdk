package com.developnetwork.meshlwahdk.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_auth.*


class AuthFragment : BaseFragment() {

    override fun getLayout(): Int {
        return R.layout.fragment_auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBTN.setOnClickListener {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToLoginFragment())
        }

        registerBTN.setOnClickListener {
findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToRegisterFirstStepFragment())
        }
    }
}