package com.developnetwork.meshlwahdk.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.ui.auth.AuthActivity
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.android.synthetic.main.fragment_terms.*
import org.koin.android.ext.android.inject


class TermsFragment : BaseFragment() {
    private val sharedPreferencesManager: SharedPreferencesManager by inject()

    override fun getLayout(): Int {
        return R.layout.fragment_terms
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        terms.text =
            HtmlCompat.fromHtml(getString(R.string.app_terms), HtmlCompat.FROM_HTML_MODE_COMPACT)

        agreeBTN.setOnClickListener {
            sharedPreferencesManager.termsAgreed = true

            val authIntent =Intent(requireContext(),AuthActivity::class.java)
            startActivity(authIntent)

            requireActivity().finishAffinity()
        }

        backBTN.setOnClickListener {
            findNavController().navigate(R.id.chooseLanguageFragment)
        }
    }
}