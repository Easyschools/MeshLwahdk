package com.developnetwork.meshlwahdk.ui.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_intro.*

class IntroFragment : BaseFragment() {


    override fun getLayout(): Int {
        return R.layout.fragment_intro
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skipBTN.setOnClickListener {
            findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToTermsFragment())
        }

        nextBTN.setOnClickListener {
            findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToTermsFragment())
        }
    }
}