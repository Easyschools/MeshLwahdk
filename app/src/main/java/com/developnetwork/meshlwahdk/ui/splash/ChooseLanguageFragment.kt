package com.developnetwork.meshlwahdk.ui.splash

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.utils.managers.LocaleManager
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.android.synthetic.main.fragment_choose_language.*
import org.koin.android.ext.android.inject

class ChooseLanguageFragment : BaseFragment() {
    private val localeManager: LocaleManager by inject()
    private val sharedPreferencesManager: SharedPreferencesManager by inject()

    override fun getLayout(): Int {
        return R.layout.fragment_choose_language
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arabicBTN.setOnClickListener {
            changeLanguage(LocaleManager.LANGUAGE_Arabic)
        }

        englishBTN.setOnClickListener {
            changeLanguage(LocaleManager.LANGUAGE_ENGLISH)
        }
    }

    private fun changeLanguage(lang: String) {

        if(localeManager.language!=lang)
        localeManager.setNewLocale(
            requireContext(),
            lang
        )

        sharedPreferencesManager.choseLanguage = true

        findNavController().navigate(ChooseLanguageFragmentDirections.actionChooseLanguageFragmentToIntroFragment())
        requireActivity().recreate()
    }
}